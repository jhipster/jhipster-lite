package tech.jhipster.lite.generator.server.springboot.mvc.springdoc.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.lite.generator.server.springboot.mvc.springdoc.domain.SpringDocConstants.*;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

public class SpringDocDomainService implements SpringDocService {

  private static final String SOURCE = "server/springboot/mvc/springdoc";
  private static final String DESTINATION = "technical/infrastructure/primary/springdoc";

  private final BuildToolService buildToolService;
  private final ProjectRepository projectRepository;
  private final SpringBootCommonService springBootCommonService;

  public SpringDocDomainService(
    BuildToolService buildToolService,
    ProjectRepository projectRepository,
    SpringBootCommonService springBootCommonService
  ) {
    this.buildToolService = buildToolService;
    this.projectRepository = projectRepository;
    this.springBootCommonService = springBootCommonService;
  }

  @Override
  public void init(Project project) {
    addSpringDocDependency(project);
    addJavaFiles(project);
    addProperties(project);
  }

  @Override
  public void addSpringDocDependency(Project project) {
    buildToolService.addProperty(project, "springdoc-openapi-ui", SpringDoc.springDocVersion());
    buildToolService.addDependency(project, SpringDoc.springDocDependency());
  }

  @Override
  public void addJavaFiles(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    project.addDefaultConfig(BASE_NAME);
    getDefaultSpringDocConfig().forEach((key, defaultValue) -> updateEmptyConfig(project, key, defaultValue));

    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));
    projectRepository.template(
      project,
      getPath(SOURCE, "src"),
      "SpringDocConfiguration.java",
      getPath(MAIN_JAVA, packageNamePath, DESTINATION)
    );
  }

  @Override
  public void addProperties(Project project) {
    getSpringDocProperties().forEach((k, v) -> springBootCommonService.addProperties(project, k, v));
  }

  private TreeMap<String, Object> getSpringDocProperties() {
    TreeMap<String, Object> result = new TreeMap<>();

    result.put("springdoc.swagger-ui.operationsSorter", DEFAULT_SWAGGER_UI_SORT_VALUE);
    result.put("springdoc.swagger-ui.tagsSorter", DEFAULT_SWAGGER_UI_SORT_VALUE);
    result.put("springdoc.swagger-ui.tryItOutEnabled", DEFAULT_TRY_OUT_ENABLED);

    return result;
  }

  private Map<String, String> getDefaultSpringDocConfig() {
    Map<String, String> result = new HashMap<>();
    result.put(API_TITLE_CONFIG_KEY, DEFAULT_API_TITLE);
    result.put(API_DESCRIPTION_CONFIG_KEY, DEFAULT_API_DESCRIPTION);
    result.put(API_LICENSE_NAME_CONFIG_KEY, DEFAULT_LICENSE_NAME);
    result.put(API_LICENSE_URL_CONFIG_KEY, DEFAULT_LICENSE_URL);
    result.put(API_EXT_DOC_DESCRIPTION_CONFIG_KEY, DEFAULT_EXT_DOC_DESCRIPTION);
    result.put(API_EXT_DOC_URL_CONFIG_KEY, DEFAULT_EXT_DOC_URL);

    return result;
  }

  private void updateEmptyConfig(Project project, String configKey, String defaultValue) {
    project.addConfig(configKey, project.getConfig(configKey).orElse(defaultValue));
  }
}
