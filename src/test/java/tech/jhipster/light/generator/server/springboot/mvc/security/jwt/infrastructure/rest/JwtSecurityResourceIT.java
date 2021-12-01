package tech.jhipster.light.generator.server.springboot.mvc.security.jwt.infrastructure.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tech.jhipster.light.generator.server.springboot.mvc.security.jwt.application.JwtSecurityAssertFiles.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.light.IntegrationTest;
import tech.jhipster.light.TestUtils;
import tech.jhipster.light.common.domain.FileUtils;
import tech.jhipster.light.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.light.generator.init.application.InitApplicationService;
import tech.jhipster.light.generator.project.domain.Project;
import tech.jhipster.light.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.light.generator.project.infrastructure.secondary.GitUtils;
import tech.jhipster.light.generator.server.javatool.base.application.JavaBaseApplicationService;
import tech.jhipster.light.generator.server.springboot.core.application.SpringBootApplicationService;
import tech.jhipster.light.generator.server.springboot.mvc.web.application.SpringBootMvcApplicationService;

@IntegrationTest
@AutoConfigureMockMvc
class JwtSecurityResourceIT {

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  JavaBaseApplicationService javaBaseApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Autowired
  SpringBootMvcApplicationService springBootMvcApplicationService;

  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldInitBasicAuth() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class);
    projectDTO.folder(FileUtils.tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    GitUtils.init(project.getFolder());
    initApplicationService.init(project);
    mavenApplicationService.init(project);
    javaBaseApplicationService.init(project);
    springBootApplicationService.init(project);
    springBootMvcApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/servers/spring-boot/mvc/security/jwt")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    assertPomXmlProperties(project);
    assertJwtSecurityFilesExists(project);
    assertJwtSecurityProperties(project);
  }
}
