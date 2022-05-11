package tech.jhipster.lite.generator.buildtool.gradle.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

import java.util.Map;

public class Gradle {

  public static final String GRADLE_NEEDLE_DEPENDENCY = "// jhipster-needle-gradle-add-dependency";

  public static final String IMPLEMENTATION = "implementation(\"";
  public static final String SEPARATOR = ":";
  public static final String END_IMPLEMENTATION = "\")";
  public static final String LF = "\n";

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

  public static String getDependency(Dependency dependency) {
    return new StringBuilder()
      .append(IMPLEMENTATION)
      .append(dependency.getGroupId())
      .append(SEPARATOR)
      .append(dependency.getArtifactId())
      .append(END_IMPLEMENTATION)
      .append(LF)
      .append(GRADLE_NEEDLE_DEPENDENCY)
      .toString().indent(2);
  }
}
