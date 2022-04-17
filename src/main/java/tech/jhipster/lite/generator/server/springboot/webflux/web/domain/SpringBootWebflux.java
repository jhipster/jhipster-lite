package tech.jhipster.lite.generator.server.springboot.webflux.web.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class SpringBootWebflux {

  public static final String SPRINGBOOT_PACKAGE = "org.springframework.boot";

  private SpringBootWebflux() {}

  public static Dependency springBootStarterWebfluxDependency() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId("spring-boot-starter-webflux").build();
  }

  public static Dependency problemSpringWebfluxDependency() {
    return Dependency
      .builder()
      .groupId("org.zalando")
      .artifactId("problem-spring-webflux")
      .version("\\${problem-spring-webflux.version}")
      .build();
  }

  public static Dependency springBootStarterValidation() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId("spring-boot-starter-validation").build();
  }
}
