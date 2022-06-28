package tech.jhipster.lite.generator.server.hexagonaldocumentation.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.server.hexagonaldocumentation.domain.HexagonalDocumentationModuleFactory;

@Service
public class HexagonalArchitectureDocumentationApplicationService {

  private final HexagonalDocumentationModuleFactory factory;

  public HexagonalArchitectureDocumentationApplicationService() {
    factory = new HexagonalDocumentationModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
