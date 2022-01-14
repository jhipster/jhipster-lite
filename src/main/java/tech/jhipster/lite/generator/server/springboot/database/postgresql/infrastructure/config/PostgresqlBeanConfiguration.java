package tech.jhipster.lite.generator.server.springboot.database.postgresql.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.database.postgresql.domain.PostgresqlDomainService;
import tech.jhipster.lite.generator.server.springboot.database.postgresql.domain.PostgresqlService;

@Configuration
public class PostgresqlBeanConfiguration {

  public final ProjectRepository projectRepository;
  public final BuildToolService buildToolService;
  public final SpringBootCommonService springBootCommonService;

  public PostgresqlBeanConfiguration(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootCommonService springBootCommonService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
  }

  @Bean
  public PostgresqlService postgresqlService() {
    return new PostgresqlDomainService(projectRepository, buildToolService, springBootCommonService);
  }
}
