package tech.jhipster.lite.generator.client.angular.security.oauth2.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.angular.security.oauth2.application.AngularOauth2ApplicationService;
import tech.jhipster.lite.module.domain.properties.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.infrastructure.primary.JHipsterModuleApiDoc;
import tech.jhipster.lite.module.infrastructure.primary.JHipsterModuleResource;

@Configuration
class AngularOAuth2ModuleConfiguration {

  @Bean
  JHipsterModuleResource angularOAuth2Module(AngularOauth2ApplicationService angularOAuth2) {
    return JHipsterModuleResource
      .builder()
      .legacyUrl("/api/clients/angular/oauth2")
      .slug("angular-oauth2")
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addIndentation().build())
      .apiDoc(new JHipsterModuleApiDoc("Angular", "Add OAuth2 authentication"))
      .tags("client", "angular")
      .factory(angularOAuth2::buildModule);
  }
}
