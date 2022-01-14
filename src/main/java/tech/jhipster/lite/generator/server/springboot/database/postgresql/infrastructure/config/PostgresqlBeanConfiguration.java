package tech.jhipster.lite.generator.server.springboot.database.postgresql.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.database.postgresql.domain.PostgresqlDomainService;
import tech.jhipster.lite.generator.server.springboot.database.postgresql.domain.PostgresqlService;
import tech.jhipster.lite.generator.server.springboot.logging.domain.SpringBootLoggingService;

@Configuration
public class PostgresqlBeanConfiguration {

  public final ProjectRepository projectRepository;
  public final BuildToolService buildToolService;
  public final SpringBootCommonService springBootCommonService;
  public final SpringBootLoggingService springBootLoggingService;

  public PostgresqlBeanConfiguration(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootCommonService springBootCommonService,
    SpringBootLoggingService springBootLoggingService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
    this.springBootLoggingService = springBootLoggingService;
  }

  @Bean
  public PostgresqlService postgresqlService() {
    return new PostgresqlDomainService(projectRepository, buildToolService, springBootCommonService, springBootLoggingService);
  }
}
