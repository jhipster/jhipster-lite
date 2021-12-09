package tech.jhipster.lite.generator.server.springboot.mvc.web.application;

import java.util.List;

public class SpringBootMvcAssertFiles {

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

  public static List<String> zalandoProblemDependency() {
    return List.of(
      "<dependency>",
      "<groupId>org.zalando</groupId>",
      "<artifactId>problem-spring-web</artifactId>",
      "<version>${problem-spring-web.version}</version>",
      "</dependency>"
    );
  }

  public static List<String> springBootStarterValidationDependency() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-starter-validation</artifactId>",
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
