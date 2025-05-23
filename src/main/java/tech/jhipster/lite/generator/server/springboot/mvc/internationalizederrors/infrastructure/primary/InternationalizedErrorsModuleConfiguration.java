package tech.jhipster.lite.generator.server.springboot.mvc.internationalizederrors.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.mvc.internationalizederrors.application.InternationalizedErrorsApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;
import tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug;

@Configuration
class InternationalizedErrorsModuleConfiguration {

  @Bean
  JHipsterModuleResource internationalizedErrorsModule(InternationalizedErrorsApplicationService internationalizedErrors) {
    return JHipsterModuleResource.builder()
      .slug(JHLiteModuleSlug.INTERNATIONALIZED_ERRORS)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addProjectBaseName().build())
      .apiDoc("Spring Boot", "Add internationalization for application errors")
      .organization(
        JHipsterModuleOrganization.builder()
          .addDependency(JHLiteModuleSlug.JAVA_ENUMS)
          .addDependency(JHLiteModuleSlug.SPRING_BOOT_MVC_EMPTY)
          .build()
      )
      .tags("server", "spring")
      .factory(internationalizedErrors::buildModule);
  }
}
