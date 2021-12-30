package tech.jhipster.lite.generator.server.springboot.consul.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.server.springboot.consul.domain.Consul.*;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBootDomainService.CONFIG_FOLDER;

import java.util.Map;
import java.util.TreeMap;
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
    buildToolService.addDependency(project, Consul.springCloudBootstrapDependency());
    buildToolService.addDependency(project, Consul.springCloudConsulConfigDependency());
    buildToolService.addDependency(project, Consul.springCloudConsulDiscoveryDependency());
  }

  @Override
  public void addProperties(Project project) {
    project.addDefaultConfig(BASE_NAME);

    projectRepository.template(project, getPath(SOURCE, "src"), "bootstrap.properties", getPath(MAIN_RESOURCES, CONFIG_FOLDER));
    projectRepository.template(project, getPath(SOURCE, "src"), "bootstrap-fast.properties", getPath(MAIN_RESOURCES, CONFIG_FOLDER));
    projectRepository.template(project, getPath(SOURCE, "test"), "bootstrap.properties", getPath(TEST_RESOURCES, CONFIG_FOLDER));

    properties().forEach((k, v) -> addProperty(project, k, v, MAIN_RESOURCES, BOOTSTRAP_PROPERTIES, NEEDLE_BOOTSTRAP_PROPERTIES));
    fastProperties()
      .forEach((k, v) -> addProperty(project, k, v, MAIN_RESOURCES, BOOTSTRAP_FAST_PROPERTIES, NEEDLE_BOOTSTRAP_FAST_PROPERTIES));
    testProperties().forEach((k, v) -> addProperty(project, k, v, TEST_RESOURCES, BOOTSTRAP_PROPERTIES, NEEDLE_BOOTSTRAP_TEST_PROPERTIES));
  }

  private Map<String, Object> properties() {
    TreeMap<String, Object> result = new TreeMap<>();

    result.put("spring.cloud.consul.config.enabled", "true");
    result.put("spring.cloud.consul.host", "localhost");
    result.put("spring.cloud.consul.port", 8500);

    // TODO other properties

    return result;
  }

  private Map<String, Object> fastProperties() {
    TreeMap<String, Object> result = new TreeMap<>();

    result.put("spring.cloud.consul.config.enabled", "false");
    result.put("spring.cloud.consul.discovery.enabled", "false");
    result.put("spring.cloud.consul.enabled", "false");

    return result;
  }

  private Map<String, Object> testProperties() {
    TreeMap<String, Object> result = new TreeMap<>();

    result.put("spring.cloud.consul.config.enabled", "false");
    result.put("spring.cloud.consul.discovery.enabled", "false");
    result.put("spring.cloud.consul.enabled", "false");

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
