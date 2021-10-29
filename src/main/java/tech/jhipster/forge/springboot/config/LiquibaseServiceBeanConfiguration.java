package tech.jhipster.forge.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.generator.domain.buildtool.maven.MavenService;
import tech.jhipster.forge.generator.domain.server.springboot.core.SpringBootService;
import tech.jhipster.forge.springboot.domain.service.LiquibaseDomainService;
import tech.jhipster.forge.springboot.domain.usecase.LiquibaseService;

@Configuration
public class LiquibaseServiceBeanConfiguration {

  public final ProjectRepository projectRepository;
  public final MavenService mavenService;
  public final SpringBootService springBootService;

  public LiquibaseServiceBeanConfiguration(
    ProjectRepository projectRepository,
    MavenService mavenService,
    SpringBootService springBootService
  ) {
    this.projectRepository = projectRepository;
    this.mavenService = mavenService;
    this.springBootService = springBootService;
  }

  @Bean
  public LiquibaseService liquibaseService() {
    return new LiquibaseDomainService(projectRepository, mavenService, springBootService);
  }
}
