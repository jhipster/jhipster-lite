package tech.jhipster.light.generator.server.springboot.database.postgresql.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.light.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.light.generator.project.domain.ProjectRepository;
import tech.jhipster.light.generator.server.springboot.database.postgresql.domain.PostgresqlDomainService;
import tech.jhipster.light.generator.server.springboot.database.postgresql.domain.PostgresqlService;
import tech.jhipster.light.generator.server.springboot.properties.domain.SpringBootPropertiesService;

@Configuration
public class PostgresqlBeanConfiguration {

  public final ProjectRepository projectRepository;
  public final BuildToolService buildToolService;
  public final SpringBootPropertiesService springBootPropertiesService;

  public PostgresqlBeanConfiguration(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootPropertiesService springBootPropertiesService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Bean
  public PostgresqlService postgresqlService() {
    return new PostgresqlDomainService(projectRepository, buildToolService, springBootPropertiesService);
  }
}
