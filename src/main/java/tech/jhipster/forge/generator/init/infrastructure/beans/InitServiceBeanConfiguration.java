package tech.jhipster.forge.generator.init.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.init.domain.InitDomainService;
import tech.jhipster.forge.generator.init.domain.InitService;
import tech.jhipster.forge.generator.project.domain.ProjectRepository;

@Configuration
public class InitServiceBeanConfiguration {

  private final ProjectRepository projectRepository;

  public InitServiceBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public InitService initService() {
    return new InitDomainService(projectRepository);
  }
}
