package tech.jhipster.lite.generator.server.springboot.apidocumentation.springdocokta.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.apidocumentation.springdocokta.domain.SpringdocOktaModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class SpringdocOktaApplicationService {

  private final SpringdocOktaModuleFactory factory;

  public SpringdocOktaApplicationService() {
    factory = new SpringdocOktaModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
