package tech.jhipster.forge.generator.init.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.generator.init.domain.InitDomainService;
import tech.jhipster.forge.generator.init.domain.InitService;

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
