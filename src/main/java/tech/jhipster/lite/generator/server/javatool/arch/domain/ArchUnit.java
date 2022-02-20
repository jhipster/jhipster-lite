package tech.jhipster.lite.generator.server.javatool.arch.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class ArchUnit {

  private ArchUnit() {}

  public static Dependency archUnitDependency() {
    return Dependency
      .builder()
      .groupId("com.tngtech.archunit")
      .artifactId("archunit-junit5-api")
      .version("\\${" + getPropertyName() + "}")
      .build();
  }

  public static String getPropertyName() {
    return "archunit-junit5.version";
  }
}
