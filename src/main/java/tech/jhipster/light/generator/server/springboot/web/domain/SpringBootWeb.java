package tech.jhipster.light.generator.server.springboot.web.domain;

import tech.jhipster.light.generator.buildtool.generic.domain.Dependency;

public class SpringBootWeb {

  private static final String SPRINGFOX_VERSION = "3.0.0";

  private SpringBootWeb() {}

  public static String springfoxVersion() {
    return SPRINGFOX_VERSION;
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
}
