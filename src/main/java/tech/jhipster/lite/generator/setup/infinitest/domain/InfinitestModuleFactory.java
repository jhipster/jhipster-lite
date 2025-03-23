package tech.jhipster.lite.generator.setup.infinitest.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.to;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class InfinitestModuleFactory {

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .gitIgnore()
        .pattern("infinitest.filters")
        .and()
      .files()
        .add(from("infinitest/template-infinitest.filters"), to("infinitest.filters"))
        .and()
      .build();
    //@formatter:on
  }
}
