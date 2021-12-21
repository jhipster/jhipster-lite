package tech.jhipster.lite.generator.server.springboot.mvc.web.infrastructure.primary.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.tmpDirForTest;
import static tech.jhipster.lite.generator.server.springboot.mvc.web.application.SpringBootMvcAssertFiles.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;

@IntegrationTest
@AutoConfigureMockMvc
class SpringBootMvcResourceIT {

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldAddSpringBootMvc() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class).folder(tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    initApplicationService.init(project);
    mavenApplicationService.init(project);
    springBootApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/servers/spring-boot/mvc/web/tomcat")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    String projectPath = projectDTO.getFolder();
    assertFileExist(projectPath, "pom.xml");
    assertFileContent(
      projectPath,
      "pom.xml",
      List.of("<groupId>org.springframework.boot</groupId>", "<artifactId>spring-boot-starter-web</artifactId>")
    );
    assertFileContent(project, "pom.xml", springBootStarterWebDependency());

    assertFileContent(projectPath, "src/main/resources/config/application.properties", "server.port=8080");
  }

  @Test
  void shouldAddSpringBootUndertow() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class).folder(tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    initApplicationService.init(project);
    mavenApplicationService.init(project);
    springBootApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/servers/spring-boot/mvc/web/undertow")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    String projectPath = projectDTO.getFolder();
    assertFileExist(projectPath, "pom.xml");
    assertFileContent(project, "pom.xml", springBootStarterWebWithoutTomcat());
    assertFileContent(project, "pom.xml", springBootStarterUndertowDependency());

    assertFileContent(projectPath, "src/main/resources/config/application.properties", "server.port=8080");
  }

  @Test
  void shouldAddSpringBootActuator() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class).folder(tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    initApplicationService.init(project);
    mavenApplicationService.init(project);
    springBootApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/servers/spring-boot/mvc/web/actuator")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    String projectPath = projectDTO.getFolder();
    assertFileExist(projectPath, "pom.xml");
    assertFileContent(project, "pom.xml", springBootStarterActuatorDependency());

    assertFileContent(projectPath, "src/main/resources/config/application.properties", "management.endpoints.web.base-path=/management");
    assertFileContent(
      projectPath,
      "src/main/resources/config/application.properties",
      "management.endpoints.web.exposure.include=configprops, env, health, info, logfile, loggers, threaddump"
    );
    assertFileContent(projectPath, "src/main/resources/config/application.properties", "management.endpoint.health.probes.enabled=true");
    assertFileContent(
      projectPath,
      "src/main/resources/config/application.properties",
      "management.endpoint.health.group.liveness.include=livenessState"
    );
    assertFileContent(
      projectPath,
      "src/main/resources/config/application.properties",
      "management.endpoint.health.group.readiness.include=readinessState"
    );
  }
}
