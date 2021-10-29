package tech.jhipster.forge.generator.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.domain.core.ProjectRepository;
import tech.jhipster.forge.generator.domain.init.InitDomainService;
import tech.jhipster.forge.generator.domain.init.InitService;

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
