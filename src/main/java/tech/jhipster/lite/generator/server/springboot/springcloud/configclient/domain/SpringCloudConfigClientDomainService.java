package tech.jhipster.lite.generator.server.springboot.springcloud.configclient.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBootDomainService.CONFIG_FOLDER;
import static tech.jhipster.lite.generator.server.springboot.springcloud.configclient.domain.SpringCloudConfig.*;

import java.util.Map;
import java.util.TreeMap;
import tech.jhipster.lite.common.domain.Base64Utils;
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

    project.addConfig("base64JwtSecret", Base64Utils.getBase64Secret());
    projectRepository.template(
      project,
      SOURCE,
      "application.config.yml",
      "src/main/docker/central-server-config/localhost-config",
      "application.yml"
    );
  }

  @Override
  public void addProperties(Project project) {
    project.addDefaultConfig(BASE_NAME);
    project.addConfig("jhipsterRegistryPassword", getJhipsterRegistryPassword());

    projectRepository.template(project, getPath(SOURCE, "src"), "bootstrap.properties", getPath(MAIN_RESOURCES, CONFIG_FOLDER));
    projectRepository.template(project, getPath(SOURCE, "src"), "bootstrap-fast.properties", getPath(MAIN_RESOURCES, CONFIG_FOLDER));
    projectRepository.template(project, getPath(SOURCE, "src", "test"), "bootstrap.properties", getPath(TEST_RESOURCES, CONFIG_FOLDER));
  }

  @Override
  public void addCloudConfigDependencies(Project project) {
    this.buildToolService.addProperty(project, "spring-cloud", getSpringCloudVersion());
    this.buildToolService.addDependencyManagement(project, springCloudDependencies());
    this.buildToolService.addDependency(project, springCloudBootstrap());
    this.buildToolService.addDependency(project, springCloudConfigClient());
  }
}
