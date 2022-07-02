package tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoc.domain;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public interface SpringdocService {
  JHipsterModule buildSpringdocModule(JHipsterModuleProperties properties);

  JHipsterModule buildSpringdocModuleWithSecurityJWT(JHipsterModuleProperties properties);
}
