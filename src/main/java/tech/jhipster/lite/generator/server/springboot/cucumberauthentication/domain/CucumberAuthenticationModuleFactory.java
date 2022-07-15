package tech.jhipster.lite.generator.server.springboot.cucumberauthentication.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterSource;
import tech.jhipster.lite.module.domain.javabuild.GroupId;
import tech.jhipster.lite.module.domain.javabuild.VersionSlug;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyScope;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class CucumberAuthenticationModuleFactory {

  private static final JHipsterSource SOURCE = from("server/springboot/cucumberauthentication");

  private static final GroupId JSON_WEBTOKEN_GROUP = groupId("io.jsonwebtoken");
  private static final VersionSlug JSON_WEBTOKEN_VERSION = versionSlug("json-web-token");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String mainClass = properties.projectBaseName().capitalized() + "App";
    String cucumberConfigurationNeedle = "classes = { " + mainClass + ".class";
    String importNeedle = "import " + properties.basePackage().get() + "." + mainClass + ";";
    String basePackagePath = properties.basePackage().path();

    //@formatter:off
    return moduleBuilder(properties)
      .documentation(documentationTitle("Cucumber authentication"), SOURCE.file("cucumber-authentication.md"))
      .javaDependencies()
        .addDependency(jsonWebTokenDependency("jjwt-api"))
        .addDependency(jsonWebTokenDependency("jjwt-impl"))
        .addDependency(jsonWebTokenDependency("jjwt-jackson"))
        .and()
      .mandatoryReplacements()
        .in("src/test/java/" + basePackagePath + "/cucumber/CucumberConfiguration.java")
          .add(text(cucumberConfigurationNeedle), cucumberTestClasses(cucumberConfigurationNeedle))
          .add(text(importNeedle), securityConfigurationImport(importNeedle, properties))
          .and()
        .and()
      .files()
        .add(
          SOURCE.template("AuthenticationSteps.java"),
          toSrcTestJava().append(basePackagePath).append("authentication/infrastructure/primary/AuthenticationSteps.java")
        )
        .add(
          SOURCE.template("CucumberAuthenticationConfiguration.java"),
          toSrcTestJava().append(basePackagePath).append("cucumber/CucumberAuthenticationConfiguration.java")
        )
        .and()
      .build();
    //@formatter:on
  }

  private String cucumberTestClasses(String cucumberConfigurationNeedle) {
    return cucumberConfigurationNeedle + ", TestSecurityConfiguration.class, CucumberAuthenticationConfiguration.class";
  }

  private String securityConfigurationImport(String importNeedle, JHipsterModuleProperties properties) {
    return (
      importNeedle +
      JHipsterModule.LINE_BREAK +
      "import " +
      properties.basePackage().get() +
      ".authentication.infrastructure.primary.TestSecurityConfiguration;"
    );
  }

  private JavaDependency jsonWebTokenDependency(String artifactId) {
    return javaDependency()
      .groupId(JSON_WEBTOKEN_GROUP)
      .artifactId(artifactId)
      .versionSlug(JSON_WEBTOKEN_VERSION)
      .scope(JavaDependencyScope.TEST)
      .build();
  }
}
