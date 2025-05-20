package tech.jhipster.lite.generator.client.hexagonaldocumentation.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.documentationTitle;
import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class FrontHexagonalDocumentationModuleFactory {

  public static final JHipsterSource SOURCE = from("client/common/hexagonal-documentation");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    return moduleBuilder(properties)
      .documentation(documentationTitle("Front hexagonal architecture"), SOURCE.template("front-hexagonal-architecture.md"))
      .build();
  }
}
