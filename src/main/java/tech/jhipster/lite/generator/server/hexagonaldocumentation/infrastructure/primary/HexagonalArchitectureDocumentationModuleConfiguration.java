package tech.jhipster.lite.generator.server.hexagonaldocumentation.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.module.infrastructure.primary.JHipsterModuleApiDoc;
import tech.jhipster.lite.generator.module.infrastructure.primary.JHipsterModuleResource;
import tech.jhipster.lite.generator.server.hexagonaldocumentation.application.HexagonalArchitectureDocumentationApplicationService;

@Configuration
class HexagonalArchitectureDocumentationModuleConfiguration {

  @Bean
  JHipsterModuleResource hexagonalArchitectureDocumentationModule(
    HexagonalArchitectureDocumentationApplicationService hexagonalArchitectureDocumentations
  ) {
    return JHipsterModuleResource
      .builder()
      .legacyUrl("/api/servers/hexagonal-architecture-documentation")
      .slug("application-service-hexagonal-architecture-documentation")
      .withoutProperties()
      .apiDoc(new JHipsterModuleApiDoc("Spring Boot", "Add documentation for hexagonal architecture"))
      .factory(hexagonalArchitectureDocumentations::buildModule);
  }
}
