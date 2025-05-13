package tech.jhipster.lite.generator.client.tools.cypressmergecoverage.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.client.tools.cypressmergecoverage.domain.CypressMergeCoverageModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class CypressMergeCoverageApplicationService {

  private final CypressMergeCoverageModuleFactory cypressMergeCoverage;

  public CypressMergeCoverageApplicationService() {
    cypressMergeCoverage = new CypressMergeCoverageModuleFactory();
  }

  public JHipsterModule buildCypressMergeCoverage(JHipsterModuleProperties properties) {
    return cypressMergeCoverage.buildCypressMergeCoverage(properties);
  }
}
