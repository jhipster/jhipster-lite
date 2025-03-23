package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.account.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.account.domain.OAuth2AccountModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class OAuth2AccountSecurityApplicationService {

  private final OAuth2AccountModuleFactory oAuth2Account;

  public OAuth2AccountSecurityApplicationService() {
    oAuth2Account = new OAuth2AccountModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return oAuth2Account.buildModule(properties);
  }
}
