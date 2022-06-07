package tech.jhipster.lite.generator.server.springboot.database.postgresql.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.docker.domain.DockerService;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.database.postgresql.domain.PostgresqlModuleFactory;
import tech.jhipster.lite.generator.server.springboot.database.sqlcommon.domain.SQLCommonService;

@Service
public class PostgresqlApplicationService {

  private final PostgresqlModuleFactory factory;

  private final DockerService dockerService;
  private final SpringBootCommonService springBootCommonService;
  private final SQLCommonService sqlCommonService;

  public PostgresqlApplicationService(
    DockerService dockerService,
    SpringBootCommonService springBootCommonService,
    SQLCommonService sqlCommonService
  ) {
    this.dockerService = dockerService;
    this.springBootCommonService = springBootCommonService;
    this.sqlCommonService = sqlCommonService;
    factory = new PostgresqlModuleFactory(this.dockerService, this.springBootCommonService, this.sqlCommonService);
  }

  public JHipsterModule build(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
