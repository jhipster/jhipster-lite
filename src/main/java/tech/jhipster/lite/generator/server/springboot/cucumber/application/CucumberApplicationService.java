package tech.jhipster.lite.generator.server.springboot.cucumber.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.module.JHipsterModules;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.server.springboot.cucumber.domain.CucumberModuleFactory;

@Service
public class CucumberApplicationService {

  private final JHipsterModules modules;
  private final CucumberModuleFactory factory;

  public CucumberApplicationService(JHipsterModules modules) {
    this.modules = modules;

    factory = new CucumberModuleFactory();
  }

  public void add(JHipsterModuleProperties properties) {
    JHipsterModule module = factory.buildModule(properties);

    modules.apply(module);
  }
}
