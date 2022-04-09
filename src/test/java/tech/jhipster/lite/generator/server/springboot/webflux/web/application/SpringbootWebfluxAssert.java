package tech.jhipster.lite.generator.server.springboot.webflux.web.application;

import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.generator.project.domain.Constants.POM_XML;

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
}
