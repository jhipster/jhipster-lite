package tech.jhipster.lite.generator.client.vue.router.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.vue.router.domain.VueRouterModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class VueRouterApplicationService {

  private final VueRouterModuleFactory vue;

  public VueRouterApplicationService() {
    vue = new VueRouterModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return vue.buildModule(properties);
  }
}
