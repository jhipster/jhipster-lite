package tech.jhipster.lite.generator.client.react.cypress.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.react.cypress.domain.CypressReactDomainService;
import tech.jhipster.lite.generator.client.react.cypress.domain.CypressReactService;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@Configuration
public class CypressReactBeanConfiguration {

  private final ProjectRepository projectRepository;
  private final NpmService npmService;

  public CypressReactBeanConfiguration(ProjectRepository projectRepository, NpmService npmService) {
    this.projectRepository = projectRepository;
    this.npmService = npmService;
  }

  @Bean
  public CypressReactService CypressReactService() {
    return new CypressReactDomainService(projectRepository, npmService);
  }
}
