package tech.jhipster.lite.generator.client.vite.vue.core.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.vite.vue.core.domain.ViteVueDomainService;
import tech.jhipster.lite.generator.client.vite.vue.core.domain.ViteVueService;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@Configuration
public class ViteVueBeanConfiguration {

  private final ProjectRepository projectRepository;
  private final NpmService npmService;

  public ViteVueBeanConfiguration(ProjectRepository projectRepository, NpmService npmService) {
    this.projectRepository = projectRepository;
    this.npmService = npmService;
  }

  @Bean
  public ViteVueService viteVueService() {
    return new ViteVueDomainService(projectRepository, npmService);
  }
}
