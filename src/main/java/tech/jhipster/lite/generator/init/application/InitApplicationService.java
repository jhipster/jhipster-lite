package tech.jhipster.lite.generator.init.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.init.domain.InitModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.nodejs.NodeVersions;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class InitApplicationService {

  private final InitModuleFactory init;

  public InitApplicationService(NodeVersions nodeVersions) {
    init = new InitModuleFactory(nodeVersions);
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return init.buildModule(properties);
  }
}
