package tech.jhipster.forge.generator.server.springboot.database.psql.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.buildtool.generic.domain.BuildToolRepository;
import tech.jhipster.forge.generator.project.domain.ProjectRepository;
import tech.jhipster.forge.generator.server.springboot.database.psql.domain.PsqlDomainService;
import tech.jhipster.forge.generator.server.springboot.database.psql.domain.PsqlService;
import tech.jhipster.forge.generator.server.springboot.properties.domain.SpringBootPropertiesService;

@Configuration
public class PsqlServiceBeanConfiguration {

  public final ProjectRepository projectRepository;
  public final BuildToolRepository buildToolRepository;
  public final SpringBootPropertiesService springBootPropertiesService;

  public PsqlServiceBeanConfiguration(
    ProjectRepository projectRepository,
    BuildToolRepository buildToolRepository,
    SpringBootPropertiesService springBootPropertiesService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolRepository = buildToolRepository;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Bean
  public PsqlService psqlService() {
    return new PsqlDomainService(projectRepository, buildToolRepository, springBootPropertiesService);
  }
}
