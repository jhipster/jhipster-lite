package tech.jhipster.lite.generator.server.webjars.htmx.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.javabuild.GroupId;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class HtmxWebjarsModuleFactory {

  private static final GroupId WEBJARS_NPM_GROUP = groupId("org.webjars.npm");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);
    //@formatter:off
    return moduleBuilder(properties)
      .javaDependencies()
      .addDependency(WEBJARS_NPM_GROUP, artifactId("htmx.org"))
      .and()
      .build();
    //@formatter:on
  }
}
