package tech.jhipster.lite.generator.server.springboot.devtools.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.devtools.domain.DevToolsModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class DevToolsApplicationService {

  private final DevToolsModuleFactory devTools;

  public DevToolsApplicationService() {
    devTools = new DevToolsModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return devTools.buildModule(properties);
  }
}
