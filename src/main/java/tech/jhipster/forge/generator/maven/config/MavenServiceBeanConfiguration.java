package tech.jhipster.forge.generator.maven.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.generator.maven.domain.MavenDomainService;
import tech.jhipster.forge.generator.maven.domain.MavenService;

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
