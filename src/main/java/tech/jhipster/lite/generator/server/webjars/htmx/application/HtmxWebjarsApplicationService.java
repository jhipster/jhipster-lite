package tech.jhipster.lite.generator.server.webjars.htmx.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.webjars.htmx.domain.HtmxWebjarsModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class HtmxWebjarsApplicationService {

  private final HtmxWebjarsModuleFactory htmxWebjarsModuleFactory;

  public HtmxWebjarsApplicationService() {
    this.htmxWebjarsModuleFactory = new HtmxWebjarsModuleFactory();
  }

  public JHipsterModule buildWebjarsModule(JHipsterModuleProperties properties) {
    return htmxWebjarsModuleFactory.buildModule(properties);
  }
}
