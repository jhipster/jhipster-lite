package tech.jhipster.lite.generator.server.springboot.cache.ehcache.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.cache.ehcache.domain.EHCacheModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class EhcacheApplicationService {

  private final EHCacheModuleFactory factory;

  public EhcacheApplicationService() {
    factory = new EHCacheModuleFactory();
  }

  public JHipsterModule buildJavaConfigurationModule(JHipsterModuleProperties properties) {
    return factory.buildJavaConfigurationModule(properties);
  }

  public JHipsterModule buildXmlConfigurationModule(JHipsterModuleProperties properties) {
    return factory.buildXmlConfigurationModule(properties);
  }
}
