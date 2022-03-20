package tech.jhipster.lite.generator.history.infrastructure.primary;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;

@IntegrationTest
@AutoConfigureMockMvc
class HistoryResourceIT {

  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldNotGetHistoryWithoutFolder() throws Exception {
    mockMvc.perform(get("/api/projects/history")).andExpect(status().isBadRequest());
  }

  @Test
  void shouldGetHistory() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class);
    if (projectDTO == null) {
      throw new GeneratorException("Error when reading file");
    }
    projectDTO.folder(FileUtils.tmpDirForTest());

    mockMvc
      .perform(post("/api/projects/init").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO)))
      .andExpect(status().isOk());
    mockMvc
      .perform(
        post("/api/build-tools/maven").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());
    mockMvc
      .perform(
        post("/api/github-actions/maven").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    mockMvc
      .perform(get("/api/projects/history").param("folder", projectDTO.getFolder()))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.serviceIds", Matchers.hasSize(3)))
      .andExpect(jsonPath("$.serviceIds[0]").value("init"))
      .andExpect(jsonPath("$.serviceIds[1]").value("maven"))
      .andExpect(jsonPath("$.serviceIds[2]").value("github-actions"));
  }
}
