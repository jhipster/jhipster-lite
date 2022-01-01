package tech.jhipster.lite.generator.server.springboot.consul.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.server.springboot.consul.domain.Consul.*;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBootDomainService.CONFIG_FOLDER;

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

    projectRepository.template(project, getPath(SOURCE, "src"), BOOTSTRAP_PROPERTIES, getPath(MAIN_RESOURCES, CONFIG_FOLDER));
    projectRepository.template(project, getPath(SOURCE, "src"), BOOTSTRAP_FAST_PROPERTIES, getPath(MAIN_RESOURCES, CONFIG_FOLDER));
    projectRepository.template(project, getPath(SOURCE, "test"), BOOTSTRAP_PROPERTIES, getPath(TEST_RESOURCES, CONFIG_FOLDER));
  }
}
