package tech.jhipster.forge.generator.server.springboot.core.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.buildtool.maven.domain.MavenService;
import tech.jhipster.forge.generator.project.domain.ProjectRepository;
import tech.jhipster.forge.generator.server.springboot.core.domain.SpringBootDomainService;
import tech.jhipster.forge.generator.server.springboot.core.domain.SpringBootService;

@Configuration
public class SpringBootServiceBeanConfiguration {

  private final ProjectRepository projectRepository;
  private final MavenService mavenService;

  public SpringBootServiceBeanConfiguration(ProjectRepository projectRepository, MavenService mavenService) {
    this.projectRepository = projectRepository;
    this.mavenService = mavenService;
  }

  @Bean
  public SpringBootService springBootService() {
    return new SpringBootDomainService(projectRepository, mavenService);
  }
}
