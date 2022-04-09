package tech.jhipster.lite.generator.server.springboot.webflux.web.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class SpringBootWebflux {

  public static final String SPRINGBOOT_PACKAGE = "org.springframework.boot";

  private SpringBootWebflux() {}

  public static Dependency springBootStarterWebfluxDependency() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId("spring-boot-starter-webflux").build();
  }
}
