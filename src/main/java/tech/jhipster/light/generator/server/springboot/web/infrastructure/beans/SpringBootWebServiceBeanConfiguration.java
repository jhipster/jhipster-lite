package tech.jhipster.light.generator.server.springboot.web.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.light.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.light.generator.project.domain.ProjectRepository;
import tech.jhipster.light.generator.server.springboot.properties.domain.SpringBootPropertiesService;
import tech.jhipster.light.generator.server.springboot.web.domain.SpringBootWebDomainService;
import tech.jhipster.light.generator.server.springboot.web.domain.SpringBootWebService;

@Configuration
public class SpringBootWebServiceBeanConfiguration {

  public final ProjectRepository projectRepository;
  public final BuildToolService buildToolService;
  public final SpringBootPropertiesService springBootPropertiesService;

  public SpringBootWebServiceBeanConfiguration(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootPropertiesService springBootPropertiesService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Bean
  public SpringBootWebService springBootWebService() {
    return new SpringBootWebDomainService(projectRepository, buildToolService, springBootPropertiesService);
  }
}
