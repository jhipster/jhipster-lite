package tech.jhipster.forge.generator.server.springboot.core.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.forge.generator.project.domain.ProjectRepository;
import tech.jhipster.forge.generator.server.springboot.core.domain.SpringBootDomainService;
import tech.jhipster.forge.generator.server.springboot.core.domain.SpringBootService;

@Configuration
public class SpringBootServiceBeanConfiguration {

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;

  public SpringBootServiceBeanConfiguration(ProjectRepository projectRepository, BuildToolService buildToolService) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
  }

  @Bean
  public SpringBootService springBootService() {
    return new SpringBootDomainService(projectRepository, buildToolService);
  }
}
