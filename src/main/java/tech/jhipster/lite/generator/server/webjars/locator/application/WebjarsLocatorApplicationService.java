package tech.jhipster.lite.generator.server.webjars.locator.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.webjars.locator.domain.WebjarsLocatorModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class WebjarsLocatorApplicationService {

  private final WebjarsLocatorModuleFactory webjarsModuleFactory;

  public WebjarsLocatorApplicationService() {
    webjarsModuleFactory = new WebjarsLocatorModuleFactory();
  }

  public JHipsterModule buildWebjarsModule(JHipsterModuleProperties properties) {
    return webjarsModuleFactory.buildModule(properties);
  }
}
