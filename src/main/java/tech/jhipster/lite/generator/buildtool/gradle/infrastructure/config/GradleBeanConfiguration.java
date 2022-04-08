package tech.jhipster.lite.generator.buildtool.gradle.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.gradle.domain.GradleDomainService;
import tech.jhipster.lite.generator.buildtool.gradle.domain.GradleService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@Configuration
public class GradleBeanConfiguration {

  private final ProjectRepository projectRepository;

  public GradleBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public GradleService gradleService() {
    return new GradleDomainService(projectRepository);
  }
}
