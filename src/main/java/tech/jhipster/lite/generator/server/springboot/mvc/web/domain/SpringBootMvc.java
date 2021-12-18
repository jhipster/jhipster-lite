package tech.jhipster.lite.generator.server.springboot.mvc.web.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class SpringBootMvc {

  private static final String PROBLEM_SPRING_VERSION = "0.27.0";

  private SpringBootMvc() {}

  public static String problemSpringVersion() {
    return PROBLEM_SPRING_VERSION;
  }

  public static Dependency springBootStarterWebDependency() {
    return Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-web").build();
  }

  public static Dependency tomcatDependency() {
    return Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-tomcat").build();
  }

  public static Dependency undertowDependency() {
    return Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-undertow").build();
  }

  public static Dependency springfoxDependency() {
    return Dependency.builder().groupId("io.springfox").artifactId("springfox-boot-starter").version("\\${springfox.version}").build();
  }

  public static Dependency problemSpringDependency() {
    return Dependency.builder().groupId("org.zalando").artifactId("problem-spring-web").version("\\${problem-spring-web.version}").build();
  }

  public static Dependency springBootStarterValidation() {
    return Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-validation").build();
  }
}
