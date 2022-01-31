package tech.jhipster.lite.generator.server.springboot.mvc.web.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class SpringBootMvc {

  public static final String SPRINGBOOT_PACKAGE = "org.springframework.boot";

  private SpringBootMvc() {}

  public static Dependency springBootStarterWebDependency() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId("spring-boot-starter-web").build();
  }

  public static Dependency springBootActuatorDependency() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId("spring-boot-starter-actuator").build();
  }

  public static Dependency tomcatDependency() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId("spring-boot-starter-tomcat").build();
  }

  public static Dependency undertowDependency() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId("spring-boot-starter-undertow").build();
  }

  public static Dependency problemSpringDependency() {
    return Dependency.builder().groupId("org.zalando").artifactId("problem-spring-web").version("\\${problem-spring-web.version}").build();
  }

  public static Dependency springBootStarterValidation() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId("spring-boot-starter-validation").build();
  }
}
