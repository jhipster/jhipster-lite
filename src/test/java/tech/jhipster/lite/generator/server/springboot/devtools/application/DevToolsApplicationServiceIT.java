package tech.jhipster.lite.generator.server.springboot.devtools.application;

import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.TestUtils.tmpProject;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.POM_XML;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.APPLICATION_FAST_PROPERTIES;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.APPLICATION_PROPERTIES;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;

@IntegrationTest
class DevToolsApplicationServiceIT {

  @Autowired
  DevToolsApplicationService devToolsApplicationService;

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    devToolsApplicationService.init(project);

    assertPomContainsDependency(project);

    assertProperties(project);
  }

  @Test
  void shouldAddSpringBootDevTools() {
    Project project = tmpProject();
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);

    devToolsApplicationService.addSpringBootDevTools(project);

    assertPomContainsDependency(project);
  }

  @Test
  void shouldAddProperties() {
    Project project = tmpProject();
    project.addConfig(PACKAGE_NAME, "tech.jhipster.chips");
    project.addConfig(BASE_NAME, "chips");
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    devToolsApplicationService.addProperties(project);

    assertProperties(project);
  }

  private List<String> springBootDevTools() {
    return List.of(
      "<dependency>",
      "<groupId>org.springframework.boot</groupId>",
      "<artifactId>spring-boot-devtools</artifactId>",
      "<optional>true</optional>",
      "</dependency>"
    );
  }

  private void assertPomContainsDependency(Project project) {
    assertFileContent(project, POM_XML, springBootDevTools());
  }

  private void assertProperties(Project project) {
    assertFileContent(
      project,
      getPath(MAIN_RESOURCES, "config", APPLICATION_PROPERTIES),
      List.of("spring.devtools.livereload.enabled=false", "spring.devtools.restart.enabled=false")
    );
    assertFileContent(
      project,
      getPath(MAIN_RESOURCES, "config", APPLICATION_FAST_PROPERTIES),
      List.of("spring.devtools.livereload.enabled=true", "spring.devtools.restart.enabled=true")
    );
  }
}
