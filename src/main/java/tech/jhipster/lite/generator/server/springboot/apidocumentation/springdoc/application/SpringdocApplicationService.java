package tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoc.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoc.domain.SpringdocService;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class SpringdocApplicationService {

  private final SpringdocService springdocService;

  public SpringdocApplicationService(SpringdocService springdocService) {
    this.springdocService = springdocService;
  }

  public JHipsterModule buildSpringdocModule(JHipsterModuleProperties properties) {
    return springdocService.buildSpringdocModule(properties);
  }

  public JHipsterModule buildSpringdocModuleWithSecurityJWT(JHipsterModuleProperties properties) {
    return springdocService.buildSpringdocModuleWithSecurityJWT(properties);
  }
}
