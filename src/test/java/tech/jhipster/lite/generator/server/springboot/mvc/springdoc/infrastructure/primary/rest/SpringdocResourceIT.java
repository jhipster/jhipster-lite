package tech.jhipster.lite.generator.server.springboot.mvc.springdoc.infrastructure.primary.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.server.springboot.mvc.springdoc.application.SpringdocAssert.*;
import static tech.jhipster.lite.generator.server.springboot.mvc.springdoc.domain.SpringdocConstants.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;

@IntegrationTest
@AutoConfigureMockMvc
class SpringdocResourceIT {

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldInit() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class).folder(FileUtils.tmpDirForTest());
    projectDTO.getGeneratorJhipster().put(BASE_NAME, "MyProject");

    Project project = ProjectDTO.toProject(projectDTO);

    initApplicationService.init(project);
    mavenApplicationService.init(project);
    springBootApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/servers/spring-boot/mvc/springdoc/init")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    assertDependencies(project);
    assertJavaFiles(project);
    assertProperties(project);

    assertFileContent(project, SPRING_DOC_CONFIG_JAVA_FILE_NAME, "myProject");
    assertFileContent(project, SPRING_DOC_CONFIG_JAVA_FILE_NAME, DEFAULT_API_TITLE);
    assertFileContent(project, SPRING_DOC_CONFIG_JAVA_FILE_NAME, DEFAULT_API_DESCRIPTION);
    assertFileContent(project, SPRING_DOC_CONFIG_JAVA_FILE_NAME, DEFAULT_LICENSE_NAME);
    assertFileContent(project, SPRING_DOC_CONFIG_JAVA_FILE_NAME, DEFAULT_LICENSE_URL);
    assertFileContent(project, SPRING_DOC_CONFIG_JAVA_FILE_NAME, DEFAULT_EXT_DOC_DESCRIPTION);
    assertFileContent(project, SPRING_DOC_CONFIG_JAVA_FILE_NAME, DEFAULT_EXT_DOC_URL);
  }

  @Test
  void shouldInitWithSecurityJWT() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class);
    projectDTO.folder(FileUtils.tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);

    initApplicationService.init(project);
    mavenApplicationService.init(project);
    springBootApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/servers/spring-boot/mvc/springdoc/init-with-security-jwt")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    assertDependencies(project);
    assertJavaFilesWithSecurityJWT(project);
    assertProperties(project);
  }
}
