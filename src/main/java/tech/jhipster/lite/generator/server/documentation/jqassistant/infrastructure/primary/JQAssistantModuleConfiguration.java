package tech.jhipster.lite.generator.server.documentation.jqassistant.infrastructure.primary;

import static tech.jhipster.lite.generator.slug.domain.JHLiteModuleSlug.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.documentation.jqassistant.application.JQAssistantApplicationService;
import tech.jhipster.lite.generator.slug.domain.JHLiteFeatureSlug;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class JQAssistantModuleConfiguration {

  @Bean
  JHipsterModuleResource jQAssistantModule(JQAssistantApplicationService jqassistant) {
    return JHipsterModuleResource.builder()
      .slug(JQASSISTANT)
      .withoutProperties()
      .apiDoc("Documentation", "Setup jQAssistant for documentation and analysis of the project")
      .organization(JHipsterModuleOrganization.builder().addDependency(JHLiteFeatureSlug.JAVA_BUILD_TOOL).build())
      .tags("server", "documentation")
      .factory(jqassistant::buildModule);
  }

  @Bean
  JHipsterModuleResource jQAssistantJMoleculesModule(JQAssistantApplicationService jqassistant) {
    return JHipsterModuleResource.builder()
      .slug(JQASSISTANT_JMOLECULES)
      .withoutProperties()
      .apiDoc("Documentation", "Add jMolecules support for jQAssistant")
      .organization(JHipsterModuleOrganization.builder().addDependency(JQASSISTANT).addDependency(JMOLECULES).build())
      .tags("server", "documentation")
      .factory(jqassistant::buildJMoleculesModule);
  }
}
