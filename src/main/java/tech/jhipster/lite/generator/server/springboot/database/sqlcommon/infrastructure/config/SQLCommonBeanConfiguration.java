package tech.jhipster.lite.generator.server.springboot.database.sqlcommon.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.database.sqlcommon.domain.SQLCommonDomainService;
import tech.jhipster.lite.generator.server.springboot.database.sqlcommon.domain.SQLCommonService;

@Configuration
public class SQLCommonBeanConfiguration {

  public final BuildToolService buildToolService;
  public final SpringBootCommonService springBootCommonService;

  public SQLCommonBeanConfiguration(BuildToolService buildToolService, SpringBootCommonService springBootCommonService) {
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
  }

  @Bean
  public SQLCommonService sqlCommonService() {
    return new SQLCommonDomainService(buildToolService, springBootCommonService);
  }
}
