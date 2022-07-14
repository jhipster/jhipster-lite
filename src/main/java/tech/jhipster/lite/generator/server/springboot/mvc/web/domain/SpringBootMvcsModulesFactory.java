package tech.jhipster.lite.generator.server.springboot.mvc.web.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModule.JHipsterModuleBuilder;
import tech.jhipster.lite.module.domain.JHipsterSource;
import tech.jhipster.lite.module.domain.javabuild.ArtifactId;
import tech.jhipster.lite.module.domain.javabuild.GroupId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.module.domain.javaproperties.PropertyKey;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.module.domain.replacement.TextNeedleBeforeReplacer;

public class SpringBootMvcsModulesFactory {

  private static final JHipsterSource SOURCE = from("server/springboot/mvc/web");

  private static final GroupId SPRING_BOOT_GROUP = groupId("org.springframework.boot");
  private static final ArtifactId STARTER_WEB_ARTIFACT_ID = artifactId("spring-boot-starter-web");

  private static final PropertyKey SERVER_PORT = propertyKey("server.port");

  private static final String CORS_PRIMARY = "technical/infrastructure/primary/cors";

  private static final TextNeedleBeforeReplacer LOGS_NEEDLE = lineBeforeText("<!-- jhipster-needle-logback-add-log -->");
  private static final String SPRING_MVC_LOGGER = "<logger name=\"org.springframework.web\" level=\"ERROR\" />";
  private static final String UNDERTOW_LOGGER = "<logger name=\"io.undertow\" level=\"WARN\" />";

  public JHipsterModule buildTomcatModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String packagePath = properties.basePackage().path();

    //@formatter:off
    return springMvcBuilder(properties, packagePath, SPRING_MVC_LOGGER)
      .javaDependencies()
        .addDependency(SPRING_BOOT_GROUP, STARTER_WEB_ARTIFACT_ID)
        .and()
      .build();
    //@formatter:on
  }

  public JHipsterModule buildUntertowModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String packagePath = properties.basePackage().path();

    //@formatter:off
    return springMvcBuilder(properties, packagePath, UNDERTOW_LOGGER)
      .javaDependencies()
        .addDependency(springBootWebWithoutTomcatDependency())
        .addDependency(SPRING_BOOT_GROUP, artifactId("spring-boot-starter-undertow"))
        .and()
      .build();
    //@formatter:on
  }

  private JavaDependency springBootWebWithoutTomcatDependency() {
    return javaDependency()
      .groupId(SPRING_BOOT_GROUP)
      .artifactId(STARTER_WEB_ARTIFACT_ID)
      .addExclusion(SPRING_BOOT_GROUP, artifactId("spring-boot-starter-tomcat"))
      .build();
  }

  private JHipsterModuleBuilder springMvcBuilder(JHipsterModuleProperties properties, String packagePath, String logger) {
    //@formatter:off
    return moduleBuilder(properties)
      .documentation(documentationTitle("CORS configuration"), SOURCE.file("cors-configuration.md"))
      .javaDependencies()
        .addDependency(SPRING_BOOT_GROUP, artifactId("spring-boot-starter-validation"))
        .and()
      .springMainProperties()
        .set(SERVER_PORT, propertyValue(properties.serverPort().stringValue()))
        .and()
      .springTestProperties()
        .set(SERVER_PORT, propertyValue("0"))
        .and()
      .files()
        .batch(SOURCE.append("src/cors"), toSrcMainJava().append(packagePath).append(CORS_PRIMARY))
          .template("CorsFilterConfiguration.java")
          .template("CorsProperties.java")
          .and()
        .add(
          SOURCE.append("test/cors").template("CorsFilterConfigurationIT.java"),
          toSrcTestJava().append(packagePath).append(CORS_PRIMARY).append("CorsFilterConfigurationIT.java")
        )
        .add(SOURCE.append("test").template("JsonHelper.java"), toSrcTestJava().append(packagePath).append("JsonHelper.java"))
        .and()
      .optionalReplacements()
        .in("src/main/resources/logback-spring.xml")
          .add(LOGS_NEEDLE, properties.indentation().spaces() + logger)
          .and()
        .in("src/test/resources/logback.xml")
          .add(LOGS_NEEDLE, properties.indentation().spaces() + logger)
          .and()
        .and();
    //@formatter:on
  }
}
