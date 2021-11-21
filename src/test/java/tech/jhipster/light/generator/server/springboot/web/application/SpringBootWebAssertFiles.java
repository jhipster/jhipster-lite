package tech.jhipster.light.generator.server.springboot.web.application;

import java.util.List;

public class SpringBootWebAssertFiles {

  public static List<String> springBootStarterWebDependency() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-starter-web</artifactId>",
      "</dependency>"
    );
  }

  public static List<String> springBootStarterUndertowDependency() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-starter-undertow</artifactId>",
      "</dependency>"
    );
  }

  public static List<String> springBootStarterWebWithoutTomcat() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-starter-web</artifactId>",
      "<exclusions>",
      "<exclusion>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-starter-tomcat</artifactId>",
      "</exclusion>",
      "</exclusions>",
      "</dependency>"
    );
  }

  public static List<String> springFoxDependency() {
    return List.of(
      "<dependency>",
      "<groupId>io.springfox</groupId>",
      "<artifactId>springfox-boot-starter</artifactId>",
      "<version>${springfox.version}</version>",
      "</dependency>"
    );
  }
}
