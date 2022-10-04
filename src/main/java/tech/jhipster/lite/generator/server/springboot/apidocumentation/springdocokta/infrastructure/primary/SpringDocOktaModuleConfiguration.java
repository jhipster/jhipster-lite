package tech.jhipster.lite.generator.server.springboot.apidocumentation.springdocokta.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.apidocumentation.springdocokta.application.SpringDocOktaApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class SpringDocOktaModuleConfiguration {

  @Bean
  JHipsterModuleResource springDocOktaModule(SpringDocOktaApplicationService springdocOkta) {
    return JHipsterModuleResource
      .builder()
      .slug("springdoc-oauth2-okta")
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addIndentation().build())
      .apiDoc("Spring Boot - API Documentation", "Add Okta authentication for springdoc")
      .organization(
        JHipsterModuleOrganization
          .builder()
          .addFeatureDependency("springdoc")
          .addModuleDependency("springdoc-oauth2")
          .addModuleDependency("spring-boot-oauth2-okta")
          .build()
      )
      .tags("server", "swagger", "springdoc", "okta")
      .factory(springdocOkta::buildModule);
  }
}
