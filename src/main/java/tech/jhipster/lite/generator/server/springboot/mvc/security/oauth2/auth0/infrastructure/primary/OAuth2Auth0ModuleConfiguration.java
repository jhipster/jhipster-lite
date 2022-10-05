package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.auth0.infrastructure.primary;

import static tech.jhipster.lite.module.domain.resource.JHipsterModulePropertyDefinition.auth0Domain;
import static tech.jhipster.lite.module.domain.resource.JHipsterModulePropertyDefinition.clientId;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.auth0.application.OAuth2Auth0SecurityApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class OAuth2Auth0ModuleConfiguration {

  private static final String AUTHENTICATION = "authentication";

  @Bean
  JHipsterModuleResource oAuth2OktaModule(OAuth2Auth0SecurityApplicationService oAuth2) {
    return JHipsterModuleResource
      .builder()
      .slug("spring-boot-oauth2-auth0")
      .propertiesDefinition(initPropertiesDefinition())
      .apiDoc(
        "Spring Boot - MVC - Security",
        "Add a Spring Security: OAuth 2.0 / OIDC Authentication / Auth0 Provider (stateful, works with Keycloak and Auth0)"
      )
      .organization(JHipsterModuleOrganization.builder().addModuleDependency("spring-boot-oauth2").build())
      .tags("server", "spring", "spring-boot", AUTHENTICATION, "auth0")
      .factory(oAuth2::buildOAuth2Auth0Module);
  }

  private JHipsterModulePropertiesDefinition initPropertiesDefinition() {
    return JHipsterModulePropertiesDefinition
      .builder()
      .addProjectBaseName()
      .addProjectName()
      .add(auth0Domain())
      .add(clientId())
      .addIndentation()
      .build();
  }
}
