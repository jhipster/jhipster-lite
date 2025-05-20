package tech.jhipster.lite.generator.client.restpagination.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.restpagination.domain.TSRestPaginationModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class TSRestPaginationApplicationService {

  private static final TSRestPaginationModuleFactory factory = new TSRestPaginationModuleFactory();

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
