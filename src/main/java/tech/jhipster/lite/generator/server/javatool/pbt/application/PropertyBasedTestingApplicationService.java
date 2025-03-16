package tech.jhipster.lite.generator.server.javatool.pbt.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.javatool.pbt.domain.PropertyBasedTestingModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class PropertyBasedTestingApplicationService {

  private final PropertyBasedTestingModuleFactory propertyBasedTesting;

  public PropertyBasedTestingApplicationService() {
    propertyBasedTesting = new PropertyBasedTestingModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return propertyBasedTesting.buildModule(properties);
  }
}
