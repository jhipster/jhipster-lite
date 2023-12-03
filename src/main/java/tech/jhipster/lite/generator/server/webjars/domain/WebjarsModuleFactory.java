package tech.jhipster.lite.generator.server.webjars.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.javabuild.GroupId;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class WebjarsModuleFactory {

  private static final GroupId WEBJARS_GROUP = groupId("org.webjars");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .javaDependencies()
      .addDependency(WEBJARS_GROUP, artifactId("webjars-locator"))
      .and()
      .build();
    //@formatter:on
  }
}
