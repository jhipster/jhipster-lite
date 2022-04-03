package tech.jhipster.lite.generator.client.tools.cypress.infrastructure.primary;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tech.jhipster.lite.TestUtils.readFileToObject;
import static tech.jhipster.lite.common.domain.FileUtils.tmpDirForTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.generator.client.tools.cypress.application.CypressAssert;
import tech.jhipster.lite.generator.client.vue.core.application.VueApplicationService;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;

@IntegrationTest
@AutoConfigureMockMvc
class CypressResourceIT {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  VueApplicationService vueApplicationService;

  @Test
  void shouldInit() throws Exception {
    ProjectDTO projectDTO = readFileToObject("json/chips.json", ProjectDTO.class).folder(tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    initApplicationService.init(project);
    vueApplicationService.addVue(project);

    mockMvc
      .perform(post("/api/e2e/cypress").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO)))
      .andExpect(status().isOk());

    CypressAssert.assertDependency(project);
    CypressAssert.assertCypressScripts(project);
    CypressAssert.assertCypressFiles(project);
    CypressAssert.assertCypressFiles(project);
    CypressAssert.assertCypressTestFiles(project);
  }
}
