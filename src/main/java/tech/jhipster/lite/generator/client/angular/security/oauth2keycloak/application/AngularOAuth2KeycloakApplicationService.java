package tech.jhipster.lite.generator.client.angular.security.oauth2keycloak.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.angular.security.oauth2keycloak.domain.AngularOAuth2KeycloakModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class AngularOAuth2KeycloakApplicationService {

  private final AngularOAuth2KeycloakModuleFactory angularOauth2;

  public AngularOAuth2KeycloakApplicationService() {
    angularOauth2 = new AngularOAuth2KeycloakModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return angularOauth2.buildModule(properties);
  }
}
