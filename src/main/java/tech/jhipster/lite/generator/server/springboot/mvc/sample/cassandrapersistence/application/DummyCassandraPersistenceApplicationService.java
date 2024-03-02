package tech.jhipster.lite.generator.server.springboot.mvc.sample.cassandrapersistence.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.mvc.sample.cassandrapersistence.domain.DummyCassandraPersistenceModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class DummyCassandraPersistenceApplicationService {

  private final DummyCassandraPersistenceModuleFactory factory;

  public DummyCassandraPersistenceApplicationService() {
    factory = new DummyCassandraPersistenceModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
