package tech.jhipster.lite.generator.client.svelte.core.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.svelte.core.domain.SvelteModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.nodejs.NodeLazyPackagesInstaller;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class SvelteApplicationService {

  private final SvelteModuleFactory svelte;

  public SvelteApplicationService(NodeLazyPackagesInstaller nodeLazyPackagesInstaller) {
    this.svelte = new SvelteModuleFactory(nodeLazyPackagesInstaller);
  }

  public JHipsterModule buildModule(JHipsterModuleProperties project) {
    return svelte.buildModule(project);
  }
}
