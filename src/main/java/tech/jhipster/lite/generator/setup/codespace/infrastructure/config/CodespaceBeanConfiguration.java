package tech.jhipster.lite.generator.setup.codespace.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.setup.codespace.domain.CodespaceDomainService;
import tech.jhipster.lite.generator.setup.codespace.domain.CodespaceService;

@Configuration
public class CodespaceBeanConfiguration {

  private final ProjectRepository projectRepository;

  public CodespaceBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public CodespaceService CodespaceService() {
    return new CodespaceDomainService(projectRepository);
  }
}
