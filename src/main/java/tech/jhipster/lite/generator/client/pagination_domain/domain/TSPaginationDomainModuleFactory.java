package tech.jhipster.lite.generator.client.pagination_domain.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class TSPaginationDomainModuleFactory {

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    JHipsterSource source = from("client/pagination/domain");
    JHipsterDestination destination = to("src/main/webapp/app/shared/pagination/domain");

    // @formatter:off
    return moduleBuilder(properties)
      .files()
        .add(source.template("Page.ts"), destination.append("Page.ts"))
        .add(source.template("DisplayedOnPage.ts"), destination.append("DisplayedOnPage.ts"))
        .and()
      .build();
    //@formatter:on
  }
}
