package tech.jhipster.lite.generator.init.infrastructure.primary.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.TestUtils.convertObjectToJsonBytes;
import static tech.jhipster.lite.TestUtils.readFileToObject;
import static tech.jhipster.lite.generator.init.application.InitAssertFiles.assertFilesInit;
import static tech.jhipster.lite.generator.init.application.InitAssertFiles.assertFilesInitMinimal;
import static tech.jhipster.lite.generator.project.domain.Constants.PACKAGE_JSON;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;

@IntegrationTest
@AutoConfigureMockMvc
class InitResourceIT {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  InitApplicationService initApplicationService;

  @Test
  void shouldInit() throws Exception {
    ProjectDTO projectDTO = readFileToObject("json/chips.json", ProjectDTO.class).folder(FileUtils.tmpDirForTest());

    mockMvc
      .perform(post("/api/inits/full").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(projectDTO)))
      .andExpect(status().isOk());

    Project project = ProjectDTO.toProject(projectDTO);
    assertFilesInit(project);
    assertFileContent(project, "README.md", "Chips Project");
    assertFileContent(project, ".prettierrc", "tabWidth: 2");
    assertFileContent(project, PACKAGE_JSON, "chips");
  }

  @Test
  void shouldInitMinimal() throws Exception {
    ProjectDTO projectDTO = readFileToObject("json/chips.json", ProjectDTO.class).folder(FileUtils.tmpDirForTest());

    mockMvc
      .perform(post("/api/inits/minimal").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(projectDTO)))
      .andExpect(status().isOk());

    Project project = ProjectDTO.toProject(projectDTO);
    assertFilesInitMinimal(project);
    assertFileContent(project, "README.md", "Chips Project");
  }
}
