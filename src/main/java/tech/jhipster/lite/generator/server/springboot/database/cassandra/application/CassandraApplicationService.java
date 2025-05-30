package tech.jhipster.lite.generator.server.springboot.database.cassandra.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.database.cassandra.domain.CassandraModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.docker.DockerImages;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class CassandraApplicationService {

  private final CassandraModuleFactory cassandra;

  public CassandraApplicationService(DockerImages dockerImages) {
    cassandra = new CassandraModuleFactory(dockerImages);
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return cassandra.buildModule(properties);
  }
}
