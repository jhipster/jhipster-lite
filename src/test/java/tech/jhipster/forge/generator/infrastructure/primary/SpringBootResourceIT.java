package tech.jhipster.forge.generator.infrastructure.primary;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tech.jhipster.forge.TestUtils.assertFileContent;
import static tech.jhipster.forge.TestUtils.assertFileExist;
import static tech.jhipster.forge.common.utils.FileUtils.tmpDirForTest;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.forge.IntegrationTest;
import tech.jhipster.forge.TestUtils;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.application.InitApplicationService;
import tech.jhipster.forge.generator.application.MavenApplicationService;
import tech.jhipster.forge.generator.application.SpringBootApplicationService;

@IntegrationTest
@AutoConfigureMockMvc
class SpringBootResourceIT {

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldAddSpringBoot() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/springboot.json", ProjectDTO.class).path(tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    initApplicationService.init(project);
    mavenApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/spring-boot/init").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    String projectPath = projectDTO.getPath();
    assertFileExist(projectPath, "pom.xml");
    assertFileContent(projectPath, "pom.xml", List.of("<groupId>tech.jhipster.chips</groupId>", "<artifactId>chips</artifactId>"));
    assertFileContent(
      projectPath,
      "pom.xml",
      List.of("<groupId>org.springframework.boot</groupId>", "<artifactId>spring-boot-starter</artifactId>")
    );

    assertFileExist(project, "src/main/java/tech/jhipster/chips/ChipsApp.java");
    assertFileExist(project, "src/test/java/tech/jhipster/chips/ChipsAppIT.java");

    assertFileExist(project, "src/main/resources/config/application.properties");
  }

  @Test
  void shouldAddSpringBootWeb() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/springboot.json", ProjectDTO.class).path(tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    initApplicationService.init(project);
    mavenApplicationService.init(project);
    springBootApplicationService.init(project);

    mockMvc
      .perform(post("/api/spring-boot/web").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO)))
      .andExpect(status().isOk());

    String projectPath = projectDTO.getPath();
    assertFileExist(projectPath, "pom.xml");
    assertFileContent(
      projectPath,
      "pom.xml",
      List.of("<groupId>org.springframework.boot</groupId>", "<artifactId>spring-boot-starter-web</artifactId>")
    );

    assertFileContent(projectPath, "src/main/resources/config/application.properties", "server.port=8080");
  }
}
