package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.docker.domain.DockerImages;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain.OAuth2ModuleFactory;

@Service
public class OAuth2SecurityApplicationService {

  private final OAuth2ModuleFactory factory;

  public OAuth2SecurityApplicationService(DockerImages dockerImages) {
    factory = new OAuth2ModuleFactory(dockerImages);
  }

  public JHipsterModule buildOAuth2Module(JHipsterModuleProperties properties) {
    return factory.buildOAuth2Module(properties);
  }
}
