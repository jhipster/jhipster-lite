package tech.jhipster.forge.generator.server.springboot.database.psql.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.project.domain.BuildToolRepository;
import tech.jhipster.forge.generator.project.domain.ProjectRepository;
import tech.jhipster.forge.generator.server.springboot.core.domain.SpringBootService;
import tech.jhipster.forge.generator.server.springboot.database.psql.domain.PsqlDomainService;
import tech.jhipster.forge.generator.server.springboot.database.psql.domain.PsqlService;

@Configuration
public class PsqlServiceBeanConfiguration {

  public final ProjectRepository projectRepository;
  public final BuildToolRepository buildToolRepository;
  public final SpringBootService springBootService;

  public PsqlServiceBeanConfiguration(
    ProjectRepository projectRepository,
    BuildToolRepository buildToolRepository,
    SpringBootService springBootService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolRepository = buildToolRepository;
    this.springBootService = springBootService;
  }

  @Bean
  public PsqlService psqlService() {
    return new PsqlDomainService(projectRepository, buildToolRepository, springBootService);
  }
}
