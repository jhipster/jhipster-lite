package tech.jhipster.forge.springboot.application;

import static tech.jhipster.forge.TestUtils.assertFileContent;
import static tech.jhipster.forge.TestUtils.tmpProject;
import static tech.jhipster.forge.common.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.forge.common.utils.FileUtils.getPath;
import static tech.jhipster.forge.generator.domain.server.springboot.core.SpringBoot.APPLICATION_PROPERTIES;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.forge.IntegrationTest;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.application.InitApplicationService;
import tech.jhipster.forge.generator.application.MavenApplicationService;
import tech.jhipster.forge.generator.application.SpringBootApplicationService;

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
  void shouldInit() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    springBootWebApplicationService.init(project);

    assertFileContent(project, "pom.xml", springBootStarterWebDependency());
    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=8080");
  }

  @Test
  void shouldAddSpringBootWeb() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    springBootWebApplicationService.addSpringBootWeb(project);

    assertFileContent(project, "pom.xml", springBootStarterWebDependency());
    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=8080");
  }

  @Test
  void shouldAddSpringBootWebWithServerPort() {
    Project project = tmpProject();
    project.addConfig("serverPort", 7419);
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    springBootWebApplicationService.addSpringBootWeb(project);

    assertFileContent(project, "pom.xml", springBootStarterWebDependency());
    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=7419");
  }

  @Test
  void shouldAddSpringBootWebWithInvalidServerPort() {
    Project project = tmpProject();
    project.addConfig("serverPort", "chips");
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    springBootWebApplicationService.addSpringBootWeb(project);

    assertFileContent(project, "pom.xml", springBootStarterWebDependency());
    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=8080");
  }

  @Test
  void shouldAddSpringBootUndertow() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    springBootWebApplicationService.addSpringBootUndertow(project);

    assertFileContent(project, "pom.xml", springBootStarterWebWithoutTomcat());
    assertFileContent(project, "pom.xml", springBootStarterUndertowDependency());
    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=8080");
  }

  @Test
  void shouldAddSpringBootUndertowWithServerPort() {
    Project project = tmpProject();
    project.addConfig("serverPort", 1664);
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    springBootWebApplicationService.addSpringBootUndertow(project);

    assertFileContent(project, "pom.xml", springBootStarterWebWithoutTomcat());
    assertFileContent(project, "pom.xml", springBootStarterUndertowDependency());
    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=1664");
  }

  private List<String> springBootStarterWebDependency() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-starter-web</artifactId>",
      "</dependency>"
    );
  }

  private List<String> springBootStarterUndertowDependency() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-starter-undertow</artifactId>",
      "</dependency>"
    );
  }

  private List<String> springBootStarterWebWithoutTomcat() {
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
}
