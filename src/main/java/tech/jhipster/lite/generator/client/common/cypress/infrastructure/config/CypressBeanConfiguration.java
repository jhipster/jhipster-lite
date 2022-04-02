package tech.jhipster.lite.generator.client.common.cypress.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.common.cypress.domain.CypressDomainService;
import tech.jhipster.lite.generator.client.common.cypress.domain.CypressService;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@Configuration
public class CypressBeanConfiguration {

  private final ProjectRepository projectRepository;
  private final NpmService npmService;

  public CypressBeanConfiguration(ProjectRepository projectRepository, NpmService npmService) {
    this.projectRepository = projectRepository;
    this.npmService = npmService;
  }

  @Bean
  public CypressService cypressService() {
    return new CypressDomainService(projectRepository, npmService);
  }
}
