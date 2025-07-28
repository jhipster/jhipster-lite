package tech.jhipster.lite.generator.client.vue.router.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.VUE_CORE;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.VUE_ROUTER;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.vue.router.application.VueRouterApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class VueRouterModuleConfiguration {

  @Bean
  JHipsterModuleResource vueRouterModule(VueRouterApplicationService vueRouter) {
    return JHipsterModuleResource.builder()
      .slug(VUE_ROUTER)
      .withoutProperties()
      .apiDoc("Frontend - Vue", "Add Vue Router")
      .organization(JHipsterModuleOrganization.builder().addDependency(VUE_CORE).build())
      .tags("client", "vue", "store")
      .factory(vueRouter::buildModule);
  }
}
