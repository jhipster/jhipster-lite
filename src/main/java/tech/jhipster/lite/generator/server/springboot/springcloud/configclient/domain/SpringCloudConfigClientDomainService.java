package tech.jhipster.lite.generator.server.springboot.springcloud.configclient.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBootDomainService.CONFIG_FOLDER;
import static tech.jhipster.lite.generator.server.springboot.springcloud.configclient.domain.SpringCloudConfig.*;

import java.util.Map;
import java.util.TreeMap;
import tech.jhipster.lite.common.domain.Base64Util;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.properties.domain.SpringBootPropertiesService;

public class SpringCloudConfigClientDomainService implements SpringCloudConfigClientService {

  private static final String SOURCE = "server/springboot/springcloud/configclient";

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;
  private final SpringBootPropertiesService springBootPropertiesService;

  public SpringCloudConfigClientDomainService(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootPropertiesService springBootPropertiesService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Override
  public void init(Project project) {
    addCloudConfigDependencies(project);
    addProperties(project);
    addDockerCompose(project);
  }

  @Override
  public void addDockerCompose(Project project) {
    project.addDefaultConfig(BASE_NAME);
    project.addConfig("jhipsterRegistryDockerImage", SpringCloudConfig.getJhipsterRegistryDockerImage());
    project.addConfig("jhipsterRegistryPassword", getJhipsterRegistryPassword());
    projectRepository.template(project, SOURCE, "jhipster-registry.yml", "src/main/docker", "jhipster-registry.yml");

    project.addConfig("base64JwtSecret", Base64Util.getBase64Secret());
    projectRepository.template(
      project,
      SOURCE,
      "application.config.yml",
      "src/main/docker/central-server-config/localhost-config",
      "application.yml"
    );
  }

  private void addProperties(Project project) {
    projectRepository.template(project, getPath(SOURCE, "src"), "bootstrap.properties", getPath(MAIN_RESOURCES, CONFIG_FOLDER));
    projectRepository.template(project, getPath(SOURCE, "src"), "bootstrap-fast.properties", getPath(MAIN_RESOURCES, CONFIG_FOLDER));

    String baseName = project.getBaseName().orElse("jhipster");
    cloudConfigProperties(baseName).forEach((k, v) -> springBootPropertiesService.addBootstrapProperties(project, k, v));
    springBootPropertiesService.addBootstrapProperties(project, "spring.cloud.config.fail-fast", false);
    springBootPropertiesService.addBootstrapProperties(project, "spring.cloud.config.profile", "dev");

    cloudConfigProperties(baseName).forEach((k, v) -> springBootPropertiesService.addBootstrapPropertiesFast(project, k, v));
    springBootPropertiesService.addBootstrapPropertiesFast(project, "spring.cloud.config.fail-fast", true);
    springBootPropertiesService.addBootstrapPropertiesFast(project, "spring.cloud.config.profile", "prod");
  }

  @Override
  public void addCloudConfigDependencies(Project project) {
    this.buildToolService.addProperty(project, "spring-cloud.version", getSpringCloudVersion());
    this.buildToolService.addDependencyManagement(project, springCloudDependencies());
    this.buildToolService.addDependency(project, springCloudBootstrap());
    this.buildToolService.addDependency(project, springCloudConfigClient());
  }

  private Map<String, Object> cloudConfigProperties(String baseName) {
    TreeMap<String, Object> result = new TreeMap<>();
    result.put("spring.cloud.config.retry.initial-interval", 1000);
    result.put("spring.cloud.config.retry.max-interval", 2000);
    result.put("spring.cloud.config.retry.max-attempts", 100);
    result.put("spring.cloud.config.uri", "http://admin:" + getJhipsterRegistryPassword() + "@localhost:8761/config");
    result.put("spring.cloud.config.name", baseName);
    result.put("spring.cloud.config.label", "main");
    result.put("jhipster.registry.password", getJhipsterRegistryPassword());
    return result;
  }
}
