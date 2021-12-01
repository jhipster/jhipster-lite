package tech.jhipster.light.generator.server.springboot.mvc.security.jwt.domain;

import static tech.jhipster.light.common.domain.FileUtils.getPath;
import static tech.jhipster.light.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.light.generator.project.domain.Constants.TEST_JAVA;
import static tech.jhipster.light.generator.project.domain.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.light.generator.server.springboot.mvc.security.jwt.domain.JwtSecurity.*;

import java.util.LinkedHashMap;
import java.util.Map;
import tech.jhipster.light.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.light.generator.project.domain.Project;
import tech.jhipster.light.generator.project.domain.ProjectRepository;
import tech.jhipster.light.generator.server.springboot.properties.domain.SpringBootPropertiesService;

public class JwtSecurityDomainService implements JwtSecurityService {

  public static final String SOURCE = "server/springboot/mvc/security/jwt";
  public static final String SECURITY_JWT_PATH = "security/jwt";

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;
  private final SpringBootPropertiesService springBootPropertiesService;

  public JwtSecurityDomainService(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootPropertiesService springBootPropertiesService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Override
  public void initBasicAuth(Project project) {
    projectRepository.gitInit(project);

    addPropertyAndDependency(project);
    addJavaFiles(project);
    addProperties(project);
    addGitPatch(project);
    applyGitPatchAnnotationProcessor(project);
    applyGitPatchExceptionTranslator(project);
  }

  private void applyGitPatchAnnotationProcessor(Project project) {
    projectRepository.gitApplyPatch(project, getPath(project.getFolder(), ".jhipster", annotationProcessorPatch));
  }

  private void applyGitPatchExceptionTranslator(Project project) {
    projectRepository.gitApplyPatch(project, getPath(project.getFolder(), ".jhipster", exceptionTranslatorPatch));
  }

  private void addGitPatch(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));
    project.addConfig("packageNamePath", packageNamePath);

    projectRepository.add(project, SOURCE, annotationProcessorPatch, ".jhipster");
    projectRepository.template(project, SOURCE, exceptionTranslatorPatch, ".jhipster");
  }

  private void addPropertyAndDependency(Project project) {
    buildToolService.addProperty(project, "jjwt", jjwtVersion());

    buildToolService.addDependency(project, springBootStarterSecurityDependency());
    buildToolService.addDependency(project, springBootConfigurationProcessor());
    buildToolService.addDependency(project, jjwtApiDependency());
    buildToolService.addDependency(project, jjwtImplDependency());
    buildToolService.addDependency(project, jjwtJacksonDependency());

    buildToolService.addDependency(project, springSecurityTestDependency());
  }

  private void addJavaFiles(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);

    String sourceSrc = getPath(SOURCE, "src");
    String sourceTest = getPath(SOURCE, "test");
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));
    String destinationSrc = getPath(MAIN_JAVA, packageNamePath, SECURITY_JWT_PATH);
    String destinationTest = getPath(TEST_JAVA, packageNamePath, SECURITY_JWT_PATH);

    jwtSecurityFiles()
      .forEach((javaFile, destination) -> projectRepository.template(project, sourceSrc, javaFile, getPath(destinationSrc, destination)));

    jwtTestSecurityFiles()
      .forEach((javaFile, destination) -> projectRepository.template(project, sourceTest, javaFile, getPath(destinationTest, destination)));
  }

  private void addProperties(Project project) {
    String baseName = project.getBaseName().orElse("jhipster");

    jwtProperties(baseName)
      .forEach((k, v) -> {
        springBootPropertiesService.addProperties(project, k, v);
        springBootPropertiesService.addPropertiesTest(project, k, v);
      });
  }

  private Map<String, Object> jwtProperties(String baseName) {
    Map<String, Object> result = new LinkedHashMap<>();
    result.put("spring.security.user.name", "admin");
    result.put("spring.security.user.password", "\\$2a\\$12\\$cRKS9ZURbdJIaRsKDTDUmOrH4.B.2rokv8rrkrQXr2IR2Hkna484O");
    result.put(
      "application.security.authentication.jwt.base64-secret",
      "bXktc2VjcmV0LWtleS13aGljaC1zaG91bGQtYmUtY2hhbmdlZC1pbi1wcm9kdWN0aW9uLWFuZC1iZS1iYXNlNjQtZW5jb2RlZAo="
    );
    result.put("application.security.authentication.jwt.token-validity-in-seconds", "86400");
    result.put("application.security.authentication.jwt.token-validity-in-seconds-for-remember-me", "2592000");
    result.put("application.cors.allowed-origins", "http://localhost:8100,http://localhost:9000");
    result.put("application.cors.allowed-methods", "*");
    result.put("application.cors.allowed-headers", "*");
    result.put(
      "application.cors.exposed-headers",
      "Authorization,Link,X-Total-Count,X-" + baseName + "-alert,X-" + baseName + "-error,X-" + baseName + "-params"
    );
    result.put("application.cors.allow-credentials", "true");
    result.put("application.cors.max-age", "1800");
    return result;
  }
}
