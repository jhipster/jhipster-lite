package tech.jhipster.lite.generator.buildtool.gradle.domain;

import java.util.Map;

public class Gradle {

  private Gradle() {}

  public static Map<String, String> gradleWrapper() {
    // prettier-ignore
    return Map.of(
      "gradlew", ".",
      "gradlew.bat", ".",
      "gradle-wrapper.jar", "gradle/wrapper",
      "gradle-wrapper.properties", "gradle/wrapper"
    );
  }
}
