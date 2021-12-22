package tech.jhipster.lite.generator.server.sonar.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.sonar.domain.SonarDomainService;
import tech.jhipster.lite.generator.server.sonar.domain.SonarService;

@Configuration
public class SonarBeanConfiguration {

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;

  public SonarBeanConfiguration(ProjectRepository projectRepository, BuildToolService buildToolService) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
  }

  @Bean
  public SonarService sonarService() {
    return new SonarDomainService(projectRepository, buildToolService);
  }
}
