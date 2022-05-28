package tech.jhipster.lite.generator.server.javatool.base.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.module.JHipsterModules;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.server.javatool.base.domain.JavaBaseModuleFactory;
import tech.jhipster.lite.generator.server.javatool.base.domain.JavaBaseModuleProperties;

@Service
public class JavaBaseApplicationService {

  private final JHipsterModules modules;
  private final JavaBaseModuleFactory factory;

  public JavaBaseApplicationService(JHipsterModules modules) {
    this.modules = modules;

    factory = new JavaBaseModuleFactory();
  }

  public void addJavaBase(JavaBaseModuleProperties properties) {
    JHipsterModule module = factory.buildModule(properties);

    modules.apply(properties.indentation(), module);
  }
}
