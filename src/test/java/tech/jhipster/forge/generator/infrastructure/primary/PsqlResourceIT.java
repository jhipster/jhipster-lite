package tech.jhipster.forge.generator.infrastructure.primary;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tech.jhipster.forge.TestUtils.assertFileExist;
import static tech.jhipster.forge.common.domain.Constants.MAIN_JAVA;
import static tech.jhipster.forge.common.domain.Constants.TEST_JAVA;
import static tech.jhipster.forge.common.utils.FileUtils.getPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.forge.IntegrationTest;
import tech.jhipster.forge.TestUtils;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.utils.FileUtils;
import tech.jhipster.forge.error.domain.GeneratorException;
import tech.jhipster.forge.generator.application.InitApplicationService;
import tech.jhipster.forge.generator.application.MavenApplicationService;
import tech.jhipster.forge.generator.application.SpringBootApplicationService;

@IntegrationTest
@AutoConfigureMockMvc
class PsqlResourceIT {

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
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/maven.json", ProjectDTO.class);
    if (projectDTO == null) {
      throw new GeneratorException("Error when reading file");
    }
    projectDTO.path(FileUtils.tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    initApplicationService.init(project);
    mavenApplicationService.init(project);
    springBootApplicationService.init(project);

    mockMvc
      .perform(post("/api/psql/init").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO)))
      .andExpect(status().isOk());

    String projectPath = projectDTO.getPath();
    assertFileExist(projectPath, getPath(MAIN_JAVA, "tech/jhipster/chips/technical/secondary/postgresql/FixedPostgreSQL10Dialect.java"));
    assertFileExist(
      projectPath,
      getPath(TEST_JAVA, "tech/jhipster/chips/technical/secondary/postgresql/FixedPostgreSQL10DialectTest.java")
    );

    assertFileExist(projectPath, "src/main/docker/postgresql.yml");
  }
}
