package tech.jhipster.light.generator.server.springboot.mvc.web.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.light.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.light.generator.project.domain.ProjectRepository;
import tech.jhipster.light.generator.server.springboot.mvc.web.domain.SpringBootMvcDomainService;
import tech.jhipster.light.generator.server.springboot.mvc.web.domain.SpringBootMvcService;
import tech.jhipster.light.generator.server.springboot.properties.domain.SpringBootPropertiesService;

@Configuration
public class SpringBootMvcBeanConfiguration {

  public final ProjectRepository projectRepository;
  public final BuildToolService buildToolService;
  public final SpringBootPropertiesService springBootPropertiesService;

  public SpringBootMvcBeanConfiguration(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootPropertiesService springBootPropertiesService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Bean
  public SpringBootMvcService springBootMvcService() {
    return new SpringBootMvcDomainService(projectRepository, buildToolService, springBootPropertiesService);
  }
}
