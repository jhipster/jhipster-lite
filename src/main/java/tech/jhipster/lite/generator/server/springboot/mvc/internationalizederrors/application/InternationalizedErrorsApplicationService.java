package tech.jhipster.lite.generator.server.springboot.mvc.internationalizederrors.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.mvc.internationalizederrors.domain.InternationalizedErrorsModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class InternationalizedErrorsApplicationService {

  private final InternationalizedErrorsModuleFactory internationalizedErrors;

  public InternationalizedErrorsApplicationService() {
    internationalizedErrors = new InternationalizedErrorsModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return internationalizedErrors.buildModule(properties);
  }
}
