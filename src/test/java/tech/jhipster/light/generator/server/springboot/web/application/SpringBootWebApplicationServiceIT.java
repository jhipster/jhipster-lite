package tech.jhipster.light.generator.server.springboot.web.application;

import static tech.jhipster.light.TestUtils.assertFileContent;
import static tech.jhipster.light.TestUtils.tmpProject;
import static tech.jhipster.light.common.domain.FileUtils.getPath;
import static tech.jhipster.light.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.light.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.light.generator.server.springboot.core.domain.SpringBoot.APPLICATION_PROPERTIES;
import static tech.jhipster.light.generator.server.springboot.web.application.SpringBootWebAssertFiles.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.light.IntegrationTest;
import tech.jhipster.light.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.light.generator.init.application.InitApplicationService;
import tech.jhipster.light.generator.project.domain.Project;
import tech.jhipster.light.generator.server.springboot.core.application.SpringBootApplicationService;

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

    assertMvcPathmatch(project);

    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=8080");
    assertFileContent(project, getPath(TEST_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=0");
  }

  @Test
  void shouldAddSpringBootWeb() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    springBootWebApplicationService.addSpringBootWeb(project);

    assertFileContent(project, "pom.xml", springBootStarterWebDependency());

    assertMvcPathmatch(project);

    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=8080");
    assertFileContent(project, getPath(TEST_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=0");
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

    assertMvcPathmatch(project);

    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=7419");
    assertFileContent(project, getPath(TEST_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=0");
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

    assertMvcPathmatch(project);

    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=8080");
    assertFileContent(project, getPath(TEST_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=0");
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

    assertMvcPathmatch(project);

    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=8080");
    assertFileContent(project, getPath(TEST_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=0");
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

    assertMvcPathmatch(project);

    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=1664");
    assertFileContent(project, getPath(TEST_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=0");
  }

  private void assertMvcPathmatch(Project project) {
    assertFileContent(
      project,
      getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES),
      "spring.mvc.pathmatch.matching-strategy=ant_path_matcher"
    );
    assertFileContent(
      project,
      getPath(TEST_RESOURCES, "config", APPLICATION_PROPERTIES),
      "spring.mvc.pathmatch.matching-strategy=ant_path_matcher"
    );
  }
}
