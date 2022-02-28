package tech.jhipster.lite.generator.server.springboot.mvc.web.application;

import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.*;

import java.util.List;
import tech.jhipster.lite.generator.project.domain.Project;

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

  public static List<String> springBootStarterActuatorDependency() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-starter-actuator</artifactId>",
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

  public static void assertCorsFiles(Project project) {
    String corsPath = getPath(project.getPackageNamePath().orElse("com/mycompany/myapp"), "technical/infrastructure/primary/cors");
    assertFileExist(project, getPath(MAIN_JAVA, corsPath, "CorsFilterConfiguration.java"));
    assertFileExist(project, getPath(MAIN_JAVA, corsPath, "CorsProperties.java"));
    assertFileExist(project, getPath(TEST_JAVA, corsPath, "CorsFilterConfigurationIT.java"));
  }

  public static void assertCorsProperties(Project project) {
    String baseName = project.getBaseName().orElse("jhipster");
    List<String> corsProperties = List.of(
      "application.cors.allowed-origins=http://localhost:8100,http://localhost:9000",
      "application.cors.allowed-methods=*",
      "application.cors.allowed-headers=*",
      "application.cors.exposed-headers=Authorization,Link,X-Total-Count,X-" +
      baseName +
      "-alert,X-" +
      baseName +
      "-error,X-" +
      baseName +
      "-params",
      "application.cors.allow-credentials=true",
      "application.cors.max-age=1800",
      "application.cors.allowed-origin-patterns=https://*.githubpreview.dev"
    );

    List<String> commentedCorsProperties = corsProperties.stream().map(p -> "# " + p).toList();

    assertFileContent(project, getPath(MAIN_RESOURCES, "config/application.properties"), commentedCorsProperties);
    assertFileContent(project, getPath(TEST_RESOURCES, "config/application.properties"), corsProperties);
    assertFileContent(project, getPath(MAIN_RESOURCES, "config/application-local.properties"), corsProperties);
  }
}
