package tech.jhipster.forge.generator.buildtool.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.buildtool.domain.maven.MavenDomainService;
import tech.jhipster.forge.generator.buildtool.domain.maven.MavenService;
import tech.jhipster.forge.generator.project.domain.ProjectRepository;

@Configuration
public class MavenServiceBeanConfiguration {

  private final ProjectRepository projectRepository;

  public MavenServiceBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public MavenService mavenService() {
    return new MavenDomainService(projectRepository);
  }
}
