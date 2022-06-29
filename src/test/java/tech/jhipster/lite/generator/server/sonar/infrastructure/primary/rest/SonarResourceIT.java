package tech.jhipster.lite.generator.server.sonar.infrastructure.primary.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.sonar.application.SonarAssert;
import tech.jhipster.lite.module.infrastructure.secondary.TestJHipsterModules;

@IntegrationTest
@AutoConfigureMockMvc
class SonarResourceIT {

  @Autowired
  private MavenApplicationService mavenApplicationService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldAddSonarJavaBackend() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class).folder(FileUtils.tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    TestJHipsterModules.applyInit(project);
    mavenApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/developer-tools/sonar/java-backend")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    SonarAssert.assertFiles(project);
    SonarAssert.assertPomXml(project);
  }

  @Test
  void shouldAddSonarJavaBackendAndFrontend() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class).folder(FileUtils.tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    TestJHipsterModules.applyInit(project);
    mavenApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/developer-tools/sonar/java-backend-and-frontend")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    SonarAssert.assertFiles(project);
    SonarAssert.assertFrontProperties(project);
    SonarAssert.assertPomXml(project);
  }
}
