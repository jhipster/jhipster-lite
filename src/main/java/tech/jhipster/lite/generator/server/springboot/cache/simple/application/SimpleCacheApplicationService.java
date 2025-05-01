package tech.jhipster.lite.generator.server.springboot.cache.simple.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.cache.simple.domain.SimpleCacheModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class SimpleCacheApplicationService {

  private final SimpleCacheModuleFactory simpleCache;

  public SimpleCacheApplicationService() {
    simpleCache = new SimpleCacheModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return simpleCache.buildModule(properties);
  }
}
