package tech.jhipster.lite.generator.client.vite.react.cypress.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.vite.react.cypress.domain.CypressViteReactDomainService;
import tech.jhipster.lite.generator.client.vite.react.cypress.domain.CypressViteReactService;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@Configuration
public class CypressViteReactBeanConfiguration {

  private final ProjectRepository projectRepository;
  private final NpmService npmService;

  public CypressViteReactBeanConfiguration(ProjectRepository projectRepository, NpmService npmService) {
    this.projectRepository = projectRepository;
    this.npmService = npmService;
  }

  @Bean
  public CypressViteReactService cypressViteReactService() {
    return new CypressViteReactDomainService(projectRepository, npmService);
  }
}
