package tech.jhipster.lite.generator.server.springboot.technicaltools.actuator.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.server.springboot.technicaltools.actuator.domain.SpringBootActuatorModuleFactory;

@Service
public class SpringBootActuatorApplicationService {

  private final SpringBootActuatorModuleFactory springBootActuatorFactory;

  public SpringBootActuatorApplicationService() {
    springBootActuatorFactory = new SpringBootActuatorModuleFactory();
  }

  public JHipsterModule buildSpringBootActuatorModule(JHipsterModuleProperties properties) {
    return springBootActuatorFactory.buildModule(properties);
  }
}
