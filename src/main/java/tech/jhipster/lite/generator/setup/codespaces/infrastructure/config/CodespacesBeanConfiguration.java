package tech.jhipster.lite.generator.setup.codespaces.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.setup.codespaces.domain.CodespacesDomainService;
import tech.jhipster.lite.generator.setup.codespaces.domain.CodespacesService;

@Configuration
public class CodespacesBeanConfiguration {

  private final ProjectRepository projectRepository;

  public CodespacesBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public CodespacesService codespacesService() {
    return new CodespacesDomainService(projectRepository);
  }
}
