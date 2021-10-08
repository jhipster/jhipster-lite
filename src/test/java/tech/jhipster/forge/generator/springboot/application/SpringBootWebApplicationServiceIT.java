package tech.jhipster.forge.generator.springboot.application;

import static tech.jhipster.forge.TestUtils.assertFileContent;
import static tech.jhipster.forge.TestUtils.tmpProject;
import static tech.jhipster.forge.common.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.forge.common.utils.FileUtils.getPath;
import static tech.jhipster.forge.generator.springboot.domain.service.SpringBoot.APPLICATION_PROPERTIES;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.forge.IntegrationTest;
import tech.jhipster.forge.common.domain.Project;

@IntegrationTest
class SpringBootWebApplicationServiceIT {

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Autowired
  SpringBootWebApplicationService springBootWebApplicationService;

  @Test
  void shouldAddSpringBootWeb() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.addSpringBoot(project);

    springBootWebApplicationService.addSpringBootWeb(project);

    List<String> dependency = List.of(
      "<dependency>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-starter-web</artifactId>",
      "</dependency>"
    );
    assertFileContent(project, "pom.xml", dependency);
    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=8080");
  }

  @Test
  void shouldAddSpringBootUndertow() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.addSpringBoot(project);

    springBootWebApplicationService.addSpringBootUndertow(project);

    List<String> dependency = List.of(
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
    assertFileContent(project, "pom.xml", dependency);
    assertFileContent(
      project,
      "pom.xml",
      List.of(
        "<dependency>",
        "<groupId>org.springframework.boot</groupId>",
        "<artifactId>spring-boot-starter-undertow</artifactId>",
        "</dependency>"
      )
    );
    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=8080");
  }
}
