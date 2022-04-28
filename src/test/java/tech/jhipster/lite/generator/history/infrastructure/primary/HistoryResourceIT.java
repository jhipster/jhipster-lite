package tech.jhipster.lite.generator.history.infrastructure.primary;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Clock;
import java.time.Instant;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.project.domain.GeneratorAction;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;

@IntegrationTest
@AutoConfigureMockMvc
class HistoryResourceIT {

  @Autowired
  MockMvc mockMvc;

  @Mock
  Clock clock;

  @Autowired
  GeneratorHistoryInterceptor generatorHistoryInterceptor;

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

    when(clock.instant())
      .thenReturn(
        Instant.parse("2022-01-22T14:01:54.954396664Z"),
        Instant.parse("2022-01-23T14:01:55.954396664Z"),
        Instant.parse("2022-01-24T14:01:56.954396664Z")
      );
    ReflectionTestUtils.setField(generatorHistoryInterceptor, "clock", clock);

    mockMvc
      .perform(post("/api/projects").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO)))
      .andExpect(status().isOk());
    mockMvc
      .perform(
        post("/api/build-tools/maven").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());
    mockMvc
      .perform(
        post("/api/developer-tools/github-actions")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    mockMvc
      .perform(get("/api/project-histories").param("folder", projectDTO.getFolder()))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", Matchers.hasSize(3)))
      .andExpect(jsonPath("$[0].serviceId").value(GeneratorAction.INIT))
      .andExpect(jsonPath("$[0].timestamp").value("2022-01-22 14:01:54"))
      .andExpect(jsonPath("$[1].serviceId").value(GeneratorAction.MAVEN_JAVA))
      .andExpect(jsonPath("$[1].timestamp").value("2022-01-23 14:01:55"))
      .andExpect(jsonPath("$[2].serviceId").value(GeneratorAction.GITHUB_ACTIONS))
      .andExpect(jsonPath("$[2].timestamp").value("2022-01-24 14:01:56"));
  }
}
