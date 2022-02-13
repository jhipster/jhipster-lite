package tech.jhipster.lite.generator.server.javatool.arch.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class ArchUnit {

  private static final String ARCHUNIT_VERSION = "0.22.0";

  private ArchUnit() {}

  public static Dependency archUnitDependency() {
    return Dependency
      .builder()
      .groupId("com.tngtech.archunit")
      .artifactId("archunit-junit5-api")
      .version("\\${" + getPropertyName() + "}")
      .build();
  }

  public static String getArchUnitVersion() {
    return ARCHUNIT_VERSION;
  }

  public static String getPropertyName() {
    return "archunit-junit5.version";
  }
}
