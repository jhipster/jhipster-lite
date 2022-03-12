package tech.jhipster.lite.generator.server.springboot.database.mariadb.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.docker.domain.DockerService;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.database.mariadb.domain.MariaDBDomainService;
import tech.jhipster.lite.generator.server.springboot.database.mariadb.domain.MariaDBService;
import tech.jhipster.lite.generator.server.springboot.database.sqlcommon.domain.SQLCommonService;

@Configuration
public class MariaDBBeanConfiguration {

  public final BuildToolService buildToolService;
  public final SpringBootCommonService springBootCommonService;
  public final SQLCommonService sqlCommonService;
  private final DockerService dockerService;

  public MariaDBBeanConfiguration(
    BuildToolService buildToolService,
    SpringBootCommonService springBootCommonService,
    SQLCommonService sqlCommonService,
    DockerService dockerService
  ) {
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
    this.sqlCommonService = sqlCommonService;
    this.dockerService = dockerService;
  }

  @Bean
  public MariaDBService mariadbService() {
    return new MariaDBDomainService(buildToolService, springBootCommonService, sqlCommonService, dockerService);
  }
}
