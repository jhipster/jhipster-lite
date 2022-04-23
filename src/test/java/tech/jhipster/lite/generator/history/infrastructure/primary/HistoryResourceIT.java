package tech.jhipster.lite.generator.history.infrastructure.primary;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Clock;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.common.domain.TimeUtils;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.project.domain.GeneratorAction;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;

@IntegrationTest
@AutoConfigureMockMvc
class HistoryResourceIT {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  Clock clock;

  @Test
  void shouldNotGetHistoryWithoutFolder() throws Exception {
    mockMvc.perform(get("/api/project-histories")).andExpect(status().isBadRequest());
  }

  @Test
  void shouldGetServiceIdHistory() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class);
    if (projectDTO == null) {
      throw new GeneratorException("Error when reading file");
    }
    projectDTO.folder(FileUtils.tmpDirForTest());

    mockMvc
      .perform(post("/api/inits/full").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO)))
      .andExpect(status().isOk());
    mockMvc
      .perform(
        post("/api/build-tools/maven").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());
    mockMvc
      .perform(post("/api/projects").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO)))
      .andExpect(status().isOk());
    mockMvc
      .perform(
        post("/api/developer-tools/github-actions")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    mockMvc
      .perform(get("/api/project-histories/serviceIds").param("folder", projectDTO.getFolder()))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.serviceIds", Matchers.hasSize(3)))
      .andExpect(jsonPath("$.serviceIds[0]").value(GeneratorAction.GITHUB_ACTIONS))
      .andExpect(jsonPath("$.serviceIds[1]").value(GeneratorAction.INIT))
      .andExpect(jsonPath("$.serviceIds[2]").value(GeneratorAction.MAVEN_JAVA));
  }

  @Test
  void shouldGetHistory() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class);
    if (projectDTO == null) {
      throw new GeneratorException("Error when reading file");
    }
    projectDTO.folder(FileUtils.tmpDirForTest());

    try (MockedStatic<TimeUtils> timeUtils = Mockito.mockStatic(TimeUtils.class)) {
      timeUtils.when(() -> TimeUtils.getNowTimestamp(clock)).thenReturn("20220102030405");
      mockMvc
        .perform(post("/api/projects").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO)))
        .andExpect(status().isOk());
    }
    try (MockedStatic<TimeUtils> timeUtils = Mockito.mockStatic(TimeUtils.class)) {
      timeUtils.when(() -> TimeUtils.getNowTimestamp(clock)).thenReturn("20220102030406");
      mockMvc
        .perform(
          post("/api/build-tools/maven").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO))
        )
        .andExpect(status().isOk());
    }
    try (MockedStatic<TimeUtils> timeUtils = Mockito.mockStatic(TimeUtils.class)) {
      timeUtils.when(() -> TimeUtils.getNowTimestamp(clock)).thenReturn("20220102030407");
      mockMvc
        .perform(
          post("/api/developer-tools/github-actions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.convertObjectToJsonBytes(projectDTO))
        )
        .andExpect(status().isOk());
    }

    mockMvc
      .perform(get("/api/project-histories").param("folder", projectDTO.getFolder()))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", Matchers.hasSize(3)))
      .andExpect(jsonPath("$[0].serviceId").value(GeneratorAction.INIT))
      .andExpect(jsonPath("$[0].timestamp").value("20220102030405"))
      .andExpect(jsonPath("$[1].serviceId").value(GeneratorAction.MAVEN_JAVA))
      .andExpect(jsonPath("$[1].timestamp").value("20220102030406"))
      .andExpect(jsonPath("$[2].serviceId").value(GeneratorAction.GITHUB_ACTIONS))
      .andExpect(jsonPath("$[2].timestamp").value("20220102030407"));
  }
}
