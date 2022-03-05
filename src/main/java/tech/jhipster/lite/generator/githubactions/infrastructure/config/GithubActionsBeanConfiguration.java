package tech.jhipster.lite.generator.githubactions.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.githubactions.domain.GithubActionsDomainService;
import tech.jhipster.lite.generator.githubactions.domain.GithubActionsService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@Configuration
public class GithubActionsBeanConfiguration {

  private final ProjectRepository projectRepository;

  public GithubActionsBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public GithubActionsService githubActionsService() {
    return new GithubActionsDomainService(projectRepository);
  }
}
