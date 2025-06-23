package tech.jhipster.lite.generator.prettier.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.prettier.domain.PrettierModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.nodejs.NodeLazyPackagesInstaller;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class PrettierApplicationService {

  private final PrettierModuleFactory prettier;

  public PrettierApplicationService(NodeLazyPackagesInstaller nodeLazyPackagesInstaller) {
    this.prettier = new PrettierModuleFactory(nodeLazyPackagesInstaller);
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return prettier.buildModule(properties);
  }
}
