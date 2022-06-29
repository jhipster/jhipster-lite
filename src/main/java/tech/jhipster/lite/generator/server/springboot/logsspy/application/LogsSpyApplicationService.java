package tech.jhipster.lite.generator.server.springboot.logsspy.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.server.springboot.logsspy.domain.LogsSpyModuleFactory;

@Service
public class LogsSpyApplicationService {

  private final LogsSpyModuleFactory factory;

  public LogsSpyApplicationService() {
    factory = new LogsSpyModuleFactory();
  }

  public JHipsterModule build(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
