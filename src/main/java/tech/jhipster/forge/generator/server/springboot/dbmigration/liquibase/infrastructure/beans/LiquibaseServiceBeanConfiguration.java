package tech.jhipster.forge.generator.server.springboot.dbmigration.liquibase.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.project.domain.BuildToolRepository;
import tech.jhipster.forge.generator.project.domain.ProjectRepository;
import tech.jhipster.forge.generator.server.springboot.core.domain.SpringBootService;
import tech.jhipster.forge.generator.server.springboot.dbmigration.liquibase.domain.LiquibaseDomainService;
import tech.jhipster.forge.generator.server.springboot.dbmigration.liquibase.domain.LiquibaseService;

@Configuration
public class LiquibaseServiceBeanConfiguration {

  public final ProjectRepository projectRepository;
  public final BuildToolRepository buildToolRepository;
  public final SpringBootService springBootService;

  public LiquibaseServiceBeanConfiguration(
    ProjectRepository projectRepository,
    BuildToolRepository buildToolRepository,
    SpringBootService springBootService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolRepository = buildToolRepository;
    this.springBootService = springBootService;
  }

  @Bean
  public LiquibaseService liquibaseService() {
    return new LiquibaseDomainService(projectRepository, buildToolRepository, springBootService);
  }
}
