package tech.jhipster.lite.generator.server.javatool.jacoco.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.javatool.jacoco.domain.JacocoDomainService;
import tech.jhipster.lite.generator.server.javatool.jacoco.domain.JacocoService;

@Configuration
public class JacocoBeanConfiguration {

  private final ProjectRepository projectRepository;

  public JacocoBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public JacocoService jacocoService() {
    return new JacocoDomainService(projectRepository);
  }
}
