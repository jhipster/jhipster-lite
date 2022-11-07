package tech.jhipster.lite.generator.server.springboot.database.hibernate2ndlevelcache.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class Hibernate2ndLevelCacheModuleFactory {

  private static final String DEST_SECONDARY = "technical/infrastructure/secondary/hibernate2ndlevelcache";

  private static final JHipsterSource SOURCE = from("server/springboot/database/hibernate2ndlevelcache");

  private static final JHipsterSource MAIN_SOURCE = SOURCE.append("main");
  private static final JHipsterSource TEST_SOURCE = SOURCE.append("test");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String packagePath = properties.packagePath();
    JHipsterDestination mainDestination = toSrcMainJava().append(packagePath).append(DEST_SECONDARY);
    JHipsterDestination testDestination = toSrcTestJava().append(packagePath).append(DEST_SECONDARY);

    //@formatter:off
    return moduleBuilder(properties)
      .javaDependencies()
      .addDependency(
        JavaDependency
          .builder()
          .groupId(groupId("org.hibernate"))
          .artifactId(artifactId("hibernate-jcache"))
          .build()
      )
      .and()
      .files()
      .add(MAIN_SOURCE.template("Hibernate2ndLevelCacheConfiguration.java"), mainDestination.append("Hibernate2ndLevelCacheConfiguration.java"))
      .add(TEST_SOURCE.template("Hibernate2ndLevelCacheConfigurationIT.java"), testDestination.append("Hibernate2ndLevelCacheConfigurationIT.java"))
      .and()
      .build();
    //@formatter:on
  }
}
