package tech.jhipster.lite.generator.client.tools.playwright.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.tools.playwright.domain.PlaywrightDomainService;
import tech.jhipster.lite.generator.client.tools.playwright.domain.PlaywrightService;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@Configuration
public class PlaywrightBeanConfiguration {

  private final ProjectRepository projectRepository;
  private final NpmService npmService;

  public PlaywrightBeanConfiguration(ProjectRepository projectRepository, NpmService npmService) {
    this.projectRepository = projectRepository;
    this.npmService = npmService;
  }

  @Bean
  public PlaywrightService playwrightService() {
    return new PlaywrightDomainService(projectRepository, npmService);
  }
}
