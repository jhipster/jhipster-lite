package tech.jhipster.lite.generator.server.springboot.webflux.web.application;

import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.CONFIG;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.POM_XML;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;

import java.util.List;
import tech.jhipster.lite.generator.project.domain.Project;

public class SpringbootWebfluxAssert {

  public static void assertDependencies(Project project) {
    assertFileContent(
      project,
      POM_XML,
      List.of(
        "<dependency>",
        "<groupId>org.springframework.boot</groupId>",
        "<artifactId>spring-boot-starter-webflux</artifactId>",
        "</dependency>"
      )
    );
  }

  public static void assertProperties(Project project) {
    assertFileContent(
      project,
      getPath(MAIN_RESOURCES, CONFIG, "application.properties"),
      List.of("# Spring Boot Webflux", "server.port=8080", "")
    );
    assertFileContent(project, getPath(TEST_RESOURCES, CONFIG, "application.properties"), List.of("server.port=0"));
  }
}
