package tech.jhipster.lite.generator.server.springboot.cucumberauthentication.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.cucumberauthentication.domain.CucumberAuthenticationModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class CucumberAuthenticationApplicationService {

  private final CucumberAuthenticationModuleFactory factory;

  public CucumberAuthenticationApplicationService() {
    factory = new CucumberAuthenticationModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
