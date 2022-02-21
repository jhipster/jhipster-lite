package tech.jhipster.lite.generator.server.springboot.database.mysql.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.database.mysql.domain.MySQLDomainService;
import tech.jhipster.lite.generator.server.springboot.database.mysql.domain.MySQLService;
import tech.jhipster.lite.generator.server.springboot.database.sqlcommon.domain.SQLCommonService;

@Configuration
public class MySQLBeanConfiguration {

  public final BuildToolService buildToolService;
  public final SpringBootCommonService springBootCommonService;
  public final SQLCommonService sqlCommonService;

  public MySQLBeanConfiguration(
    BuildToolService buildToolService,
    SpringBootCommonService springBootCommonService,
    SQLCommonService sqlCommonService
  ) {
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
    this.sqlCommonService = sqlCommonService;
  }

  @Bean
  public MySQLService mysqlService() {
    return new MySQLDomainService(buildToolService, springBootCommonService, sqlCommonService);
  }
}
