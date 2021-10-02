package tech.jhipster.forge.generator.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.generator.springboot.domain.service.SpringBootDomainService;
import tech.jhipster.forge.generator.springboot.domain.usecase.MavenService;
import tech.jhipster.forge.generator.springboot.domain.usecase.SpringBootService;

@Configuration
public class SpringBootServiceBeanConfiguration {

  private final ProjectRepository projectRepository;
  private final MavenService mavenService;

  public SpringBootServiceBeanConfiguration(ProjectRepository projectRepository, MavenService mavenService) {
    this.projectRepository = projectRepository;
    this.mavenService = mavenService;
  }

  @Bean
  public SpringBootService springBootService() {
    return new SpringBootDomainService(projectRepository, mavenService);
  }
}
