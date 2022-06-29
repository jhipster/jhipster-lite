package tech.jhipster.lite.generator.server.springboot.springcloud.gateway.infrastructure.primary.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.lite.generator.server.springboot.springcloud.gateway.application.GatewayAssert.*;

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
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;
import tech.jhipster.lite.module.infrastructure.secondary.TestJHipsterModules;

@IntegrationTest
@AutoConfigureMockMvc
class GatewayResourceIT {

  @Autowired
  private MavenApplicationService mavenApplicationService;

  @Autowired
  private SpringBootApplicationService springBootApplicationService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldInit() throws Exception {
    // Given
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class).folder(FileUtils.tmpDirForTest());

    Project project = ProjectDTO.toProject(projectDTO);
    TestJHipsterModules.applyInit(project);
    mavenApplicationService.init(project);
    springBootApplicationService.init(project);

    // When
    mockMvc
      .perform(
        post("/api/servers/spring-boot/distributed-systems/spring-cloud/gateway")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    // Then
    assertDependencies(project);
    assertProperties(project);
  }
}
