package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.account.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.account.application.OAuth2AccountSecurityApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class OAuth2AccountModuleConfiguration {

  private static final String AUTHENTICATION = "authentication";

  @Bean
  JHipsterModuleResource oAuth2AccountModule(OAuth2AccountSecurityApplicationService oAuth2Account) {
    return JHipsterModuleResource
      .builder()
      .slug("spring-boot-oauth2-account")
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().build())
      .apiDoc("Spring Boot - MVC - Security", "Add a account context for OAuth 2.0 / OIDC Authentication")
      .organization(JHipsterModuleOrganization.builder().addModuleDependency("spring-boot-oauth2").build())
      .tags("server", "spring", "spring-boot", AUTHENTICATION, "account", "user")
      .factory(oAuth2Account::buildOAuth2AccountModule);
  }
}
