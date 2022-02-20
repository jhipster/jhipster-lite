package tech.jhipster.lite.generator.client.vite.react.core.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.vite.react.core.domain.ViteReactDomainService;
import tech.jhipster.lite.generator.client.vite.react.core.domain.ViteReactService;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@Configuration
public class ViteReactBeanConfiguration {

  private final ProjectRepository projectRepository;
  private final NpmService npmService;

  public ViteReactBeanConfiguration(ProjectRepository projectRepository, NpmService npmService) {
    this.projectRepository = projectRepository;
    this.npmService = npmService;
  }

  @Bean
  public ViteReactService viteReactService() {
    return new ViteReactDomainService(projectRepository, npmService);
  }
}
