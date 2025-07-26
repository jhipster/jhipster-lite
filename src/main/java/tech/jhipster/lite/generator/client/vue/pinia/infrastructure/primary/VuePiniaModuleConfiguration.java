package tech.jhipster.lite.generator.client.vue.pinia.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.VUE_CORE;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.VUE_PINIA;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.vue.pinia.application.VuePiniaApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class VuePiniaModuleConfiguration {

  @Bean
  JHipsterModuleResource vuePiniaModule(VuePiniaApplicationService vuePinia) {
    return JHipsterModuleResource.builder()
      .slug(VUE_PINIA)
      .withoutProperties()
      .apiDoc("Frontend - Vue", "Add pinia for state management")
      .organization(JHipsterModuleOrganization.builder().addDependency(VUE_CORE).build())
      .tags("client", "vue", "store")
      .factory(vuePinia::buildModule);
  }
}
