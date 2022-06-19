package tech.jhipster.lite.generator.server.springboot.devtools.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.server.springboot.devtools.domain.DevToolsModuleFactory;

@Service
public class DevToolsApplicationService {

  private final DevToolsModuleFactory devToolsFactory;

  public DevToolsApplicationService() {
    devToolsFactory = new DevToolsModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return devToolsFactory.buildModule(properties);
  }
}
