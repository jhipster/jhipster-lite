package tech.jhipster.lite.generator.client.tools.cypressmergecoverage.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.CODE_COVERAGE_CLIENT;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.CYPRESS_COMPONENT_TESTS;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.CYPRESS_MERGE_COVERAGE;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.tools.cypressmergecoverage.application.CypressMergeCoverageApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class CypressMergeCoverageModuleConfiguration {

  @Bean
  JHipsterModuleResource cypressMergeCoverageModule(CypressMergeCoverageApplicationService cypressMergeCoverage) {
    return JHipsterModuleResource.builder()
      .slug(CYPRESS_MERGE_COVERAGE)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addIndentation().build())
      .apiDoc(
        "Frontend - Cypress merge coverage",
        "Merge coverage from unit test vitest and component test cypress. Not working with Angular"
      )
      .organization(JHipsterModuleOrganization.builder().feature(CODE_COVERAGE_CLIENT).addDependency(CYPRESS_COMPONENT_TESTS).build())
      .tags("client", "coverage", "cypress", "vitest")
      .factory(cypressMergeCoverage::buildCypressMergeCoverage);
  }
}
