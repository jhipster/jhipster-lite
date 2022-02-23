package tech.jhipster.lite.generator.server.javatool.arch.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class ArchUnit {

  public static final String ARCHUNIT_JUNIT5_VERSION = "archunit-junit5.version";

  private ArchUnit() {}

  public static Dependency archUnitDependency() {
    return Dependency
      .builder()
      .groupId("com.tngtech.archunit")
      .artifactId("archunit-junit5-api")
      .version("\\${" + ARCHUNIT_JUNIT5_VERSION + "}")
      .build();
  }
}
