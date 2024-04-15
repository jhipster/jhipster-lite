package tech.jhipster.lite.generator.server.springboot.localeprofile.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;
import static tech.jhipster.lite.module.domain.replacement.ReplacementCondition.*;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.buildproperties.PropertyKey;
import tech.jhipster.lite.module.domain.buildproperties.PropertyValue;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPlugin;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.module.domain.replacement.TextReplacer;
import tech.jhipster.lite.shared.error.domain.Assert;

public class LocalProfileModuleFactory {

  private static final String DELIMITER = "@";

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .javaBuildProperties()
        .set(new PropertyKey("spring.profiles.active"), new PropertyValue("-"))
        .and()
      .gradleConfigurations()
        .configuration(
          """
          tasks.build {
            dependsOn("processResources")
          }

          tasks.processResources {
            filesMatching("**/*.yml", "**/*.properties") {
              filter {
                it.replace("@spring.profiles.active@", springProfilesActive)
              }
            }
          }
          """
        )
        .and()
      .javaBuildProfiles()
        .addProfile("local")
          .activation(buildProfileActivation().activeByDefault())
          .properties()
            .set(springActiveProfileProperty(), buildPropertyValue("local"))
            .and()
          .and()
        .and()
      .mavenPlugins()
        .pluginManagement(resourcesPlugin())
        .and()
      .springMainProperties()
        .set(propertyKey("spring.profiles.active"), propertyValue(DELIMITER + springActiveProfileProperty().get() + DELIMITER))
        .and()
      .optionalReplacements()
        .in(path(".github/workflows/github-actions.yml"))
          .add(new TextReplacer(notContainingReplacement(), "./mvnw clean verify"), "./mvnw clean verify -P'!local'")
          .add(new TextReplacer(notContainingReplacement(), "./gradlew clean integrationTest --no-daemon"), "./gradlew clean integrationTest -Pprofile=local --no-daemon")
          .and()
        .in(path(".gitlab-ci.yml"))
          .add(new TextReplacer(notContainingReplacement(), "./mvnw clean verify"), "./mvnw clean verify -P'!local'")
      .add(new TextReplacer(notContainingReplacement(), "./gradlew clean integrationTest $GRADLE_CLI_OPTS"), "./gradlew clean integrationTest -Pprofile=local $GRADLE_CLI_OPTS")
          .and()
        .and()
      .build();
    //@formatter:on
  }

  private static PropertyKey springActiveProfileProperty() {
    return buildPropertyKey("spring.profiles.active");
  }

  private MavenPlugin resourcesPlugin() {
    return mavenPlugin()
      .groupId("org.apache.maven.plugins")
      .artifactId("maven-resources-plugin")
      .versionSlug("maven-resources-plugin")
      .configuration(
        """
        <useDefaultDelimiters>false</useDefaultDelimiters>
        <delimiters>
          <delimiter>%s</delimiter>
        </delimiters>
        """.formatted(DELIMITER)
      )
      .build();
  }
}
