package tech.jhipster.forge.generator.refacto.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.buildtool.domain.maven.MavenService;
import tech.jhipster.forge.generator.refacto.domain.core.ProjectRepository;
import tech.jhipster.forge.generator.refacto.domain.server.springboot.core.SpringBootService;
import tech.jhipster.forge.generator.refacto.domain.server.springboot.dbmigration.liquibase.LiquibaseDomainService;
import tech.jhipster.forge.generator.refacto.domain.server.springboot.dbmigration.liquibase.LiquibaseService;

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
