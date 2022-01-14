package tech.jhipster.lite.generator.server.springboot.database.mysql.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.database.mysql.domain.MySQLDomainService;
import tech.jhipster.lite.generator.server.springboot.database.mysql.domain.MySQLService;
import tech.jhipster.lite.generator.server.springboot.logging.domain.SpringBootLoggingService;

@Configuration
public class MySQLBeanConfiguration {

  public final ProjectRepository projectRepository;
  public final BuildToolService buildToolService;
  public final SpringBootCommonService springBootCommonService;
  public final SpringBootLoggingService springBootLoggingService;

  public MySQLBeanConfiguration(
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
  public MySQLService mysqlService() {
    return new MySQLDomainService(projectRepository, buildToolService, springBootCommonService, springBootLoggingService);
  }
}
