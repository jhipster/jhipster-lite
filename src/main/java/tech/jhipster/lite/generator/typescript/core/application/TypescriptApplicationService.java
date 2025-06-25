package tech.jhipster.lite.generator.typescript.core.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.typescript.core.domain.TypescriptModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.nodejs.NodeLazyPackagesInstaller;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class TypescriptApplicationService {

  private final TypescriptModuleFactory typescript;

  public TypescriptApplicationService(NodeLazyPackagesInstaller nodeLazyPackagesInstaller) {
    this.typescript = new TypescriptModuleFactory(nodeLazyPackagesInstaller);
  }

  public JHipsterModule buildModule(JHipsterModuleProperties project) {
    return typescript.buildModule(project);
  }
}
