package tech.jhipster.lite.generator.server.springboot.consul.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.server.springboot.consul.domain.Consul.*;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBootDomainService.CONFIG_FOLDER;

import java.util.LinkedHashMap;
import java.util.Map;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class ConsulDomainService implements ConsulService {

  public static final String SOURCE = "server/springboot/consul";

  private final BuildToolService buildToolService;
  private final ProjectRepository projectRepository;

  public ConsulDomainService(BuildToolService buildToolService, ProjectRepository projectRepository) {
    this.buildToolService = buildToolService;
    this.projectRepository = projectRepository;
  }

  @Override
  public void init(Project project) {
    addDependencies(project);
    addProperties(project);
  }

  @Override
  public void addDependencies(Project project) {
    buildToolService.addProperty(project, "spring-cloud", getSpringCloudVersion());
    buildToolService.addDependencyManagement(project, springCloudDependencyManagement());
    buildToolService.addDependency(project, springCloudBootstrapDependency());
    buildToolService.addDependency(project, springCloudConsulConfigDependency());
    buildToolService.addDependency(project, springCloudConsulDiscoveryDependency());
  }

  @Override
  public void addProperties(Project project) {
    project.addDefaultConfig(BASE_NAME);

    projectRepository.template(project, getPath(SOURCE, "src"), "bootstrap.properties", getPath(MAIN_RESOURCES, CONFIG_FOLDER));
    projectRepository.template(project, getPath(SOURCE, "src"), "bootstrap-fast.properties", getPath(MAIN_RESOURCES, CONFIG_FOLDER));
    projectRepository.template(project, getPath(SOURCE, "test"), "bootstrap.properties", getPath(TEST_RESOURCES, CONFIG_FOLDER));

    properties().forEach((k, v) -> addProperty(project, k, v, MAIN_RESOURCES, BOOTSTRAP_PROPERTIES, NEEDLE_BOOTSTRAP_PROPERTIES));

    addProperty(
      project,
      "spring.cloud.consul.enabled",
      "false",
      MAIN_RESOURCES,
      BOOTSTRAP_FAST_PROPERTIES,
      NEEDLE_BOOTSTRAP_FAST_PROPERTIES
    );
    addProperty(project, "spring.cloud.consul.enabled", "false", TEST_RESOURCES, BOOTSTRAP_PROPERTIES, NEEDLE_BOOTSTRAP_TEST_PROPERTIES);
  }

  private Map<String, Object> properties() {
    Map<String, Object> result = new LinkedHashMap<>();

    result.put("spring.cloud.consul.discovery.health-check-path", "/management/health");
    result.put("spring.cloud.consul.discovery.tags[0]", "version=@project.version@");
    result.put("spring.cloud.consul.discovery.tags[1]", "context-path=\\$\\{server.servlet.context-path:\\}");
    result.put("spring.cloud.consul.host", "localhost");
    result.put("spring.cloud.consul.port", 8500);

    return result;
  }

  private void addProperty(
    Project project,
    String key,
    Object value,
    String folderProperties,
    String fileProperties,
    String needleProperties
  ) {
    String propertiesWithNeedle = key + "=" + value + System.lineSeparator() + needleProperties;
    projectRepository.replaceText(project, getPath(folderProperties, "config"), fileProperties, needleProperties, propertiesWithNeedle);
  }
}
