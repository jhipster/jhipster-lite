package tech.jhipster.lite.generator.history.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.history.domain.GeneratorHistoryDomainService;
import tech.jhipster.lite.generator.history.domain.GeneratorHistoryService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@Configuration
public class HistoryBeanConfiguration {

  private final ProjectRepository projectRepository;

  public HistoryBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public GeneratorHistoryService historyService() {
    return new GeneratorHistoryDomainService(projectRepository);
  }
}
