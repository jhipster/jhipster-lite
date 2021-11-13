package tech.jhipster.light.generator.server.springboot.properties.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.light.generator.project.domain.ProjectRepository;
import tech.jhipster.light.generator.server.springboot.properties.domain.SpringBootPropertiesDomainService;
import tech.jhipster.light.generator.server.springboot.properties.domain.SpringBootPropertiesService;

@Configuration
public class SpringBootPropertiesBeanConfiguration {

  private final ProjectRepository projectRepository;

  public SpringBootPropertiesBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public SpringBootPropertiesService springBootPropertiesService() {
    return new SpringBootPropertiesDomainService(projectRepository);
  }
}
