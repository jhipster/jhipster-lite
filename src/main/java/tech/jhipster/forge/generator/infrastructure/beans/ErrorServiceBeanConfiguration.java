package tech.jhipster.forge.generator.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.generator.domain.server.tool.error.ErrorDomainService;
import tech.jhipster.forge.generator.domain.server.tool.error.ErrorService;

@Configuration
public class ErrorServiceBeanConfiguration {

  private final ProjectRepository projectRepository;

  public ErrorServiceBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public ErrorService errorService() {
    return new ErrorDomainService(projectRepository);
  }
}
