package tech.jhipster.lite.generator.client.pagination_domain.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.pagination_domain.application.TsPaginationDomainApplicationService;
import tech.jhipster.lite.generator.slug.domain.JHLiteModuleSlug;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class TsPaginationDomainModuleConfiguration {

  @Bean
  JHipsterModuleResource tsPaginationDomainModule(TsPaginationDomainApplicationService tsPaginationDomain) {
    return JHipsterModuleResource.builder()
      .slug(JHLiteModuleSlug.TS_PAGINATION_DOMAIN)
      .withoutProperties()
      .apiDoc("client", "Add TS domain for pagination")
      .organization(JHipsterModuleOrganization.builder().addDependency(JHLiteModuleSlug.INIT).build())
      .tags("client")
      .factory(tsPaginationDomain::buildModule);
  }
}
