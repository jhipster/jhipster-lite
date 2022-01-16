package tech.jhipster.lite.generator.server.springboot.mvc.springdoc.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class SpringDoc {

  private static final String SPRING_DOC_VERSION = "1.6.4";

  private SpringDoc() {}

  public static Dependency springDocDependency() {
    return Dependency
      .builder()
      .groupId("org.springdoc")
      .artifactId("springdoc-openapi-ui")
      .version("\\${springdoc-openapi-ui.version}")
      .build();
  }

  public static String springDocVersion() {
    return SPRING_DOC_VERSION;
  }
}
