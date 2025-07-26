package tech.jhipster.lite.generator.client.vue.pinia.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.vue.pinia.domain.VuePiniaModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class VuePiniaApplicationService {

  private final VuePiniaModuleFactory vue;

  public VuePiniaApplicationService() {
    vue = new VuePiniaModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return vue.buildModule(properties);
  }
}
