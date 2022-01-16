package tech.jhipster.lite.generator.server.springboot.docker.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.docker.domain.SpringBootDockerDomainService;
import tech.jhipster.lite.generator.server.springboot.docker.domain.SpringBootDockerService;
import tech.jhipster.lite.generator.server.springboot.properties.domain.SpringBootPropertiesService;

@Configuration
public class SpringBootDockerBeanConfiguration {

  private final ProjectRepository projectRepository;
  private final SpringBootPropertiesService springBootPropertiesService;
  private final BuildToolService buildToolService;

  public SpringBootDockerBeanConfiguration(
    ProjectRepository projectRepository,
    SpringBootPropertiesService springBootPropertiesService,
    BuildToolService buildToolService
  ) {
    this.projectRepository = projectRepository;
    this.springBootPropertiesService = springBootPropertiesService;
    this.buildToolService = buildToolService;
  }

  @Bean
  public SpringBootDockerService springBootAsyncService() {
    return new SpringBootDockerDomainService(projectRepository, springBootPropertiesService, buildToolService);
  }
}
