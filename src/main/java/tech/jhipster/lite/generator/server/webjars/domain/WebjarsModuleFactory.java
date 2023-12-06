package tech.jhipster.lite.generator.server.webjars.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.javabuild.GroupId;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class WebjarsModuleFactory {

  private static final GroupId WEBJARS_GROUP = groupId("org.webjars");
  private static final GroupId WEBJARS_NPM_GROUP = groupId("org.webjars.npm");

  public JHipsterModule buildWebjarsLocatorModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .javaDependencies()
      .addDependency(WEBJARS_GROUP, artifactId("webjars-locator"))
      .and()
      .build();
    //@formatter:on
  }

  public JHipsterModule buildWebjarsHtmxModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);
    //@formatter:off
    return moduleBuilder(properties)
      .javaDependencies()
      .addDependency(WEBJARS_NPM_GROUP, artifactId("htmx.org"))
      .and()
      .build();
    //@formatter:on
  }

  public JHipsterModule buildWebjarsAlpineJSModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);
    //@formatter:off
    return moduleBuilder(properties)
      .javaDependencies()
      .addDependency(WEBJARS_NPM_GROUP, artifactId("alpinejs"))
      .and()
      .build();
    //@formatter:on
  }
}
