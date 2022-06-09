package tech.jhipster.lite.generator.server.springboot.database.postgresql.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.docker.domain.DockerService;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.server.springboot.database.postgresql.domain.PostgresqlModuleFactory;

@Service
public class PostgresqlApplicationService {

  private final PostgresqlModuleFactory factory;

  private final DockerService dockerService;

  public PostgresqlApplicationService(DockerService dockerService) {
    this.dockerService = dockerService;
    factory = new PostgresqlModuleFactory(this.dockerService);
  }

  public JHipsterModule build(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
