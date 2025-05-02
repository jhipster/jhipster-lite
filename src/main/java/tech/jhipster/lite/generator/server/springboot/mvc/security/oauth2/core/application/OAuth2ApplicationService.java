package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.core.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.core.domain.OAuth2ModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.docker.DockerImages;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class OAuth2ApplicationService {

  private final OAuth2ModuleFactory oAuth2;

  public OAuth2ApplicationService(DockerImages dockerImages) {
    oAuth2 = new OAuth2ModuleFactory(dockerImages);
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return oAuth2.buildModule(properties);
  }
}
