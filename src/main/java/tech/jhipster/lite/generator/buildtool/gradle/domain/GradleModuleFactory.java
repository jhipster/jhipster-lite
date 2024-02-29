package tech.jhipster.lite.generator.buildtool.gradle.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import java.util.regex.Pattern;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.gradleplugin.GradlePlugin;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyScope;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.module.domain.replacement.ElementReplacer;
import tech.jhipster.lite.module.domain.replacement.RegexReplacer;
import tech.jhipster.lite.shared.error.domain.Assert;

public class GradleModuleFactory {

  private static final JHipsterSource SOURCE = from("buildtool/gradle");
  private static final Pattern BUILD_GRADLE_TASK_TEST_NEEDLE = Pattern.compile(
    """
    tasks.test {
      filter {
        includeTestsMatching("**Test*")
        excludeTestsMatching("**IT*")
        excludeTestsMatching("**CucumberTest*")
      }
      useJUnitPlatform()
    }
    """,
    Pattern.LITERAL
  );
  private static final ElementReplacer EXISTING_BUILD_GRADLE_TASK_TEST_NEEDLE = new RegexReplacer(
    (contentBeforeReplacement, replacement) -> BUILD_GRADLE_TASK_TEST_NEEDLE.matcher(contentBeforeReplacement).find(),
    BUILD_GRADLE_TASK_TEST_NEEDLE
  );
  private static final String REPLACEMENT_BUILD_GRADLE_TASK_TEST =
    """
    tasks.test {
      filter {
        includeTestsMatching("**Test*")
        excludeTestsMatching("**IT*")
        excludeTestsMatching("**CucumberTest*")
      }
      useJUnitPlatform()
      finalizedBy("jacocoTestReport")
    }
    """;

  public JHipsterModule buildGradleModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .context()
        .put("dasherizedBaseName", properties.projectBaseName().kebabCase())
        .and()
      .files()
        .batch(SOURCE, to("."))
          .addTemplate("build.gradle.kts")
          .addTemplate("settings.gradle.kts")
          .and()
        .batch(SOURCE.append("gradle"), to("gradle"))
          .addFile("libs.versions.toml")
          .and()
        .and()
        .mandatoryReplacements()
         .in(path("build.gradle.kts"))
          .add(EXISTING_BUILD_GRADLE_TASK_TEST_NEEDLE, REPLACEMENT_BUILD_GRADLE_TASK_TEST)
         .and()
        .and()
      .gradlePlugins()
        .plugin(gradleJacocoPlugin())
        .and()
      .javaDependencies()
        .addDependency(junitEngineDependency())
        .addDependency(junitParamsDependency())
        .addDependency(assertjDependency())
        .addDependency(mockitoDependency())
        .and()
      .build();
    //@formatter:on
  }

  private static GradlePlugin gradleJacocoPlugin() {
    return gradleCorePlugin()
      .id("jacoco")
      .toolVersionSlug("jacoco")
      .configuration(
        """
        jacoco {
          toolVersion = libs.versions.jacoco.get()
        }

        tasks.jacocoTestReport {
          dependsOn("test", "integrationTest")
          reports {
            xml.required.set(true)
            html.required.set(true)
          }
          executionData.setFrom(fileTree(buildDir).include("**/jacoco/test.exec", "**/jacoco/integrationTest.exec"))
        }
        """
      )
      .build();
  }

  private static JavaDependency junitEngineDependency() {
    return javaDependency()
      .groupId("org.junit.jupiter")
      .artifactId("junit-jupiter-engine")
      .versionSlug("junit-jupiter.version")
      .dependencySlug("junit-engine")
      .scope(JavaDependencyScope.TEST)
      .build();
  }

  private static JavaDependency junitParamsDependency() {
    return javaDependency()
      .groupId("org.junit.jupiter")
      .artifactId("junit-jupiter-params")
      .versionSlug("junit-jupiter.version")
      .dependencySlug("junit-params")
      .scope(JavaDependencyScope.TEST)
      .build();
  }

  private static JavaDependency assertjDependency() {
    return javaDependency()
      .groupId("org.assertj")
      .artifactId("assertj-core")
      .versionSlug("assertj.version")
      .dependencySlug("assertj")
      .scope(JavaDependencyScope.TEST)
      .build();
  }

  private static JavaDependency mockitoDependency() {
    return javaDependency()
      .groupId("org.mockito")
      .artifactId("mockito-junit-jupiter")
      .versionSlug("mockito.version")
      .dependencySlug("mockito")
      .scope(JavaDependencyScope.TEST)
      .build();
  }

  public JHipsterModule buildGradleWrapperModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .files()
        .batch(SOURCE.append("gradle/wrapper"), to("gradle/wrapper"))
          .addFile("gradle-wrapper.properties")
          .addFile("gradle-wrapper.jar")
          .and()
        .addExecutable(SOURCE.file("gradlew"), to("gradlew"))
        .addExecutable(SOURCE.file("gradlew.bat"), to("gradlew.bat"))
        .and()
      .build();
    //@formatter:on
  }
}
