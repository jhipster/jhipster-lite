package tech.jhipster.lite.generator.server.springboot.springcloud.configclient.domain;

import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.server.springboot.springcloud.configclient.domain.SpringCloudConfig.*;

import java.util.Map;
import java.util.TreeMap;
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
    //add docker
  }

  @Override
  public void addDockerCompose(Project project) {
    project.addDefaultConfig(BASE_NAME);
    project.addConfig("jhipsterRegistryDockerImage", SpringCloudConfig.getJhipsterRegistryDockerImage());
    project.addConfig("jhipsterRegistryPassword", getJhipsterRegistryPassword());
    projectRepository.template(project, SOURCE, "jhipster-registry.yml", "src/main/docker", "jhipster-registry.yml");
  }

  private void addProperties(Project project) {
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
    this.buildToolService.addDependencyManagement(project, springCloudDependencies());
    this.buildToolService.addDependency(project, springCloudBootstrap());
    this.buildToolService.addDependency(project, springCloudConfigClient());
  }

  private Map<String, Object> cloudConfigProperties(String baseName) {
    TreeMap<String, Object> result = new TreeMap<>();
    result.put("spring.cloud.config.retry.initial-interval", 1000);
    result.put("spring.cloud.config.retry.max-interval", 2000);
    result.put("spring.cloud.config.retry.max-attempts", 100);
    result.put("spring.cloud.config.uri", "http://admin:${jhipster.registry.password}@localhost:8761/config");
    result.put("spring.cloud.config.name", baseName);
    result.put("spring.cloud.config.label", "main");
    result.put("jhipster.registry.password", getJhipsterRegistryPassword());
    return result;
  }
}
