package tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoc.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class Springdoc {

  protected static final String WEBFLUX_ARTIFACT_ID = "spring-boot-starter-webflux";

  private Springdoc() {}

  public static Dependency springdocDependencyForMvc() {
    return Dependency
      .builder()
      .groupId("org.springdoc")
      .artifactId("springdoc-openapi-ui")
      .version("\\${springdoc-openapi-ui.version}")
      .build();
  }

  public static Dependency springdocDependencyForWebflux() {
    return Dependency
      .builder()
      .groupId("org.springdoc")
      .artifactId("springdoc-openapi-webflux-ui")
      .version("\\${springdoc-openapi-ui.version}")
      .build();
  }
}
