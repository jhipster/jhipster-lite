package tech.jhipster.light.generator.server.springboot.web.application;

import static tech.jhipster.light.TestUtils.*;
import static tech.jhipster.light.common.domain.FileUtils.getPath;
import static tech.jhipster.light.generator.project.domain.Constants.*;
import static tech.jhipster.light.generator.server.springboot.core.domain.SpringBoot.APPLICATION_PROPERTIES;
import static tech.jhipster.light.generator.server.springboot.web.application.SpringBootWebAssertFiles.*;

import java.util.List;
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

    assertTomcat(project);
    assertSpringFox(project);

    assertMvcPathmatch(project);
    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=8080");
    assertFileContent(project, getPath(TEST_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=0");

    assertExceptionHandler(project);
  }

  @Test
  void shouldAddSpringBootWeb() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    springBootWebApplicationService.addSpringBootWeb(project);

    assertTomcat(project);
    assertSpringFox(project);

    assertMvcPathmatch(project);
    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=8080");
    assertFileContent(project, getPath(TEST_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=0");

    assertExceptionHandler(project);
  }

  @Test
  void shouldAddSpringBootWebWithServerPort() {
    Project project = tmpProject();
    project.addConfig("serverPort", 7419);
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    springBootWebApplicationService.addSpringBootWeb(project);

    assertTomcat(project);
    assertSpringFox(project);

    assertMvcPathmatch(project);
    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=7419");
    assertFileContent(project, getPath(TEST_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=0");

    assertExceptionHandler(project);
  }

  @Test
  void shouldAddSpringBootWebWithInvalidServerPort() {
    Project project = tmpProject();
    project.addConfig("serverPort", "chips");
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    springBootWebApplicationService.addSpringBootWeb(project);

    assertTomcat(project);

    assertMvcPathmatch(project);
    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=8080");
    assertFileContent(project, getPath(TEST_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=0");

    assertExceptionHandler(project);
  }

  @Test
  void shouldAddSpringBootUndertow() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    springBootWebApplicationService.addSpringBootUndertow(project);

    assertUndertow(project);

    assertMvcPathmatch(project);

    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=8080");
    assertFileContent(project, getPath(TEST_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=0");

    assertExceptionHandler(project);
  }

  @Test
  void shouldAddSpringBootUndertowWithServerPort() {
    Project project = tmpProject();
    project.addConfig("serverPort", 1664);
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    springBootWebApplicationService.addSpringBootUndertow(project);

    assertUndertow(project);
    assertSpringFox(project);

    assertMvcPathmatch(project);
    assertFileContent(project, getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=1664");
    assertFileContent(project, getPath(TEST_RESOURCES, "config", APPLICATION_PROPERTIES), "server.port=0");

    assertExceptionHandler(project);
  }

  @Test
  void shouldAddExceptionHandler() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    springBootWebApplicationService.addExceptionHandler(project);

    assertExceptionHandler(project);
  }

  private void assertExceptionHandler(Project project) {
    assertFileContent(project, "pom.xml", "<problem-spring.version>");
    assertFileContent(project, "pom.xml", "<problem-spring-web.version>");

    assertZalandoProblem(project);
    assertSpringBootStarterValidation(project);
    assertExceptionHandlerProperties(project);
    assertExceptionHandlerFiles(project);
  }

  private void assertTomcat(Project project) {
    assertFileContent(project, "pom.xml", springBootStarterWebDependency());
  }

  private void assertUndertow(Project project) {
    assertFileContent(project, "pom.xml", springBootStarterWebWithoutTomcat());
    assertFileContent(project, "pom.xml", springBootStarterUndertowDependency());
  }

  private void assertZalandoProblem(Project project) {
    assertFileContent(project, "pom.xml", zalandoProblemDependency());
  }

  private void assertSpringBootStarterValidation(Project project) {
    assertFileContent(project, "pom.xml", springBootStarterValidationDependency());
  }

  private void assertSpringFox(Project project) {
    assertFileContent(project, "pom.xml", "<springfox.version>");
    assertFileContent(project, "pom.xml", "</springfox.version>");
    assertFileContent(project, "pom.xml", springFoxDependency());
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

  private void assertExceptionHandlerProperties(Project project) {
    assertFileContent(
      project,
      getPath(MAIN_RESOURCES, "/config/application.properties"),
      List.of(
        "application.exception.details=false",
        "application.exception.package=org.,java.,net.,javax.,com.,io.,de.,com.mycompany.myapp"
      )
    );
    assertFileContent(project, getPath(TEST_RESOURCES, "/config/application.properties"), "application.exception.package=org.,java.");
  }

  private void assertExceptionHandlerFiles(Project project) {
    String packagePath = "com/mycompany/myapp/technical/primary/exception";
    List<String> listClass = List.of(
      "BadRequestAlertException.java",
      "ErrorConstants.java",
      "ExceptionTranslator.java",
      "FieldErrorDTO.java",
      "HeaderUtil.java",
      "ProblemConfiguration.java"
    );
    listClass.forEach(javaClass -> assertFileExist(project, getPath(MAIN_JAVA, packagePath), javaClass));

    List<String> listTestClass = List.of(
      "BadRequestAlertExceptionTest.java",
      "ExceptionTranslatorIT.java",
      "ExceptionTranslatorTest.java",
      "ExceptionTranslatorTestController.java",
      "FieldErrorDTOTest.java",
      "HeaderUtilTest.java"
    );
    listTestClass.forEach(testClass -> assertFileExist(project, getPath(TEST_JAVA, packagePath), testClass));
  }
}
