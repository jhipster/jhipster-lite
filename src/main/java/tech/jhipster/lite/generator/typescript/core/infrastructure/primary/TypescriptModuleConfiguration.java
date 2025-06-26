package tech.jhipster.lite.generator.typescript.core.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.INIT;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.PRETTIER;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.TYPESCRIPT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.typescript.core.application.TypescriptApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class TypescriptModuleConfiguration {

  @Bean
  JHipsterModuleResource typescriptModule(TypescriptApplicationService typescript) {
    return JHipsterModuleResource.builder()
      .slug(TYPESCRIPT)
      .propertiesDefinition(propertiesDefinition())
      .apiDoc("Typescript", "Init Typescript project")
      .organization(JHipsterModuleOrganization.builder().addDependency(INIT).addDependency(PRETTIER).build())
      .tags("typescript")
      .factory(typescript::buildModule);
  }

  private JHipsterModulePropertiesDefinition propertiesDefinition() {
    return JHipsterModulePropertiesDefinition.builder().addNodePackageManager().build();
  }
}
