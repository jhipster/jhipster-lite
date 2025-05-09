package tech.jhipster.lite.generator.client.restpagination.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.documentationTitle;
import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.to;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class TSRestPaginationModuleFactory {

  private static final JHipsterSource SOURCE = from("client/pagination/secondary");
  private static final JHipsterDestination MAIN_DESTINATION = to("src/main/webapp/app/shared/pagination/infrastructure/secondary/");
  private static final JHipsterDestination TEST_DESTINATION = to("src/test/webapp/unit/shared/pagination/infrastructure/secondary/");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .documentation(documentationTitle("rest-page"), SOURCE.file("rest-page.md"))
      .files()
        .add(SOURCE.template("RestPage.ts"), MAIN_DESTINATION.append("RestPage.ts"))
        .add(SOURCE.template("RestPage.spec.ts"), TEST_DESTINATION.append("RestPage.spec.ts"))
        .and()
      .build();
    //@formatter:on
  }
}
