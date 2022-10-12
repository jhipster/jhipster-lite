package tech.jhipster.lite.module.infrastructure.primary;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.lite.IntegrationTest;

@IntegrationTest
@AutoConfigureMockMvc
class ModulesResourceIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldNotGetPropertiesDefinitionForUnknownModule() throws Exception {
    mockMvc
      .perform(get("/api/modules/not-a-real-module"))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.title").value("Module not-a-real-module does not exist"));
  }

  @Test
  void shouldNotApplyPatchForUnknownModule() throws Exception {
    mockMvc
      .perform(post("/api/modules/not-a-real-module/apply-patch").content("{}").contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.title").value("Module not-a-real-module does not exist"));
  }
}
