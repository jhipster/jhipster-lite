package tech.jhipster.lite.generator.setup.gitpod.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.setup.gitpod.domain.GitpodDomainService;
import tech.jhipster.lite.generator.setup.gitpod.domain.GitpodService;

@Configuration
public class GitpodBeanConfiguration {

  private final ProjectRepository projectRepository;

  public GitpodBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public GitpodService gitpodService() {
    return new GitpodDomainService(projectRepository);
  }
}
