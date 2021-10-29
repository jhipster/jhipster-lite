package tech.jhipster.forge.generator.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.domain.buildtool.maven.MavenService;
import tech.jhipster.forge.generator.domain.core.ProjectRepository;
import tech.jhipster.forge.generator.domain.server.springboot.core.SpringBootDomainService;
import tech.jhipster.forge.generator.domain.server.springboot.core.SpringBootService;

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
