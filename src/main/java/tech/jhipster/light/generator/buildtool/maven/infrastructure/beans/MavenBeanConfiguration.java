package tech.jhipster.light.generator.buildtool.maven.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.light.generator.buildtool.maven.domain.MavenDomainService;
import tech.jhipster.light.generator.buildtool.maven.domain.MavenService;
import tech.jhipster.light.generator.project.domain.ProjectRepository;

@Configuration
public class MavenBeanConfiguration {

  private final ProjectRepository projectRepository;

  public MavenBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public MavenService mavenService() {
    return new MavenDomainService(projectRepository);
  }
}
