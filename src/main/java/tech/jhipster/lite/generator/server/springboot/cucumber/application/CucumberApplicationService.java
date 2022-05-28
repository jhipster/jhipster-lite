package tech.jhipster.lite.generator.server.springboot.cucumber.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.module.JHipsterModules;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.server.springboot.cucumber.domain.CucumberModuleFactory;
import tech.jhipster.lite.generator.server.springboot.cucumber.domain.CucumberModuleProperties;

@Service
public class CucumberApplicationService {

  private final JHipsterModules modules;
  private final CucumberModuleFactory factory;

  public CucumberApplicationService(JHipsterModules modules) {
    this.modules = modules;

    factory = new CucumberModuleFactory();
  }

  public void add(CucumberModuleProperties properties) {
    JHipsterModule module = factory.buildModule(properties);

    modules.apply(properties.indentation(), module);
  }
}
