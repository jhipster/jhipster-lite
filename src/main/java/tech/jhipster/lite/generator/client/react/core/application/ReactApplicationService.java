package tech.jhipster.lite.generator.client.react.core.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.react.core.domain.ReactModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.nodejs.NodeLazyPackagesInstaller;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class ReactApplicationService {

  private final ReactModuleFactory react;

  public ReactApplicationService(NodeLazyPackagesInstaller nodeLazyPackagesInstaller) {
    react = new ReactModuleFactory(nodeLazyPackagesInstaller);
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return react.buildModule(properties);
  }
}
