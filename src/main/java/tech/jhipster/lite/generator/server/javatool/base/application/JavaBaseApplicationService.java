package tech.jhipster.lite.generator.server.javatool.base.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.module.JHipsterModules;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.server.javatool.base.domain.JavaBaseModuleFactory;

@Service
public class JavaBaseApplicationService {

  private final JHipsterModules modules;
  private final JavaBaseModuleFactory factory;

  public JavaBaseApplicationService(JHipsterModules modules) {
    this.modules = modules;

    factory = new JavaBaseModuleFactory();
  }

  public void addJavaBase(JHipsterModuleProperties properties) {
    JHipsterModule module = factory.buildModule(properties);

    modules.apply(module);
  }
}
