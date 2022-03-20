package tech.jhipster.lite.generator.server.springboot.mvc.springdoc.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class Springdoc {

  private Springdoc() {}

  public static Dependency springdocDependency() {
    return Dependency
      .builder()
      .groupId("org.springdoc")
      .artifactId("springdoc-openapi-ui")
      .version("\\${springdoc-openapi-ui.version}")
      .build();
  }
}
