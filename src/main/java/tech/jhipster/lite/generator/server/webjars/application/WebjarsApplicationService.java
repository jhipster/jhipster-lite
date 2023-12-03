package tech.jhipster.lite.generator.server.webjars.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.webjars.domain.WebjarsModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class WebjarsApplicationService {

  private final WebjarsModuleFactory webjarsModuleFactory;

  public WebjarsApplicationService() {
    webjarsModuleFactory = new WebjarsModuleFactory();
  }

  public JHipsterModule buildWebjarsModule(JHipsterModuleProperties properties) {
    return webjarsModuleFactory.buildModule(properties);
  }
}
