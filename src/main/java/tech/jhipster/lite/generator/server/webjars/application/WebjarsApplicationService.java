package tech.jhipster.lite.generator.server.webjars.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.webjars.domain.WebjarsModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class WebjarsApplicationService {

  private final WebjarsModuleFactory webjars;

  public WebjarsApplicationService() {
    webjars = new WebjarsModuleFactory();
  }

  public JHipsterModule buildWebjarsLocatorModule(JHipsterModuleProperties properties) {
    return webjars.buildWebjarsLocatorModule(properties);
  }

  public JHipsterModule buildWebjarsHtmxModule(JHipsterModuleProperties properties) {
    return webjars.buildWebjarsHtmxModule(properties);
  }

  public JHipsterModule buildWebjarsAlpineJSModule(JHipsterModuleProperties properties) {
    return webjars.buildWebjarsAlpineJSModule(properties);
  }
}
