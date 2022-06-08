package tech.jhipster.lite.generator.server.springboot.database.postgresql.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.docker.domain.DockerService;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.server.springboot.database.postgresql.domain.PostgresqlModuleFactory;

@Service
public class PostgresqlApplicationService {

  private final PostgresqlModuleFactory factory;

  private final DockerService dockerService;

  private final BuildToolService buildToolService;

  public PostgresqlApplicationService(DockerService dockerService, BuildToolService buildToolService) {
    this.dockerService = dockerService;
    this.buildToolService = buildToolService;
    factory = new PostgresqlModuleFactory(this.dockerService, this.buildToolService);
  }

  public JHipsterModule build(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
