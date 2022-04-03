package tech.jhipster.lite.generator.server.javatool.base.infrastructure.primary.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.common.domain.FileUtils.tmpDirForTest;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVA;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;

@IntegrationTest
@AutoConfigureMockMvc
class JavaBaseResourceIT {

  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldAddJavaBase() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class).folder(tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);

    mockMvc
      .perform(
        post("/api/servers/java/base").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    String pathMain = getPath(project.getFolder(), MAIN_JAVA, "tech/jhipster/chips/error/domain");
    assertFileExist(getPath(pathMain, "Assert.java"));
    assertFileExist(getPath(pathMain, "MissingMandatoryValueException.java"));
    assertFileExist(getPath(pathMain, "UnauthorizedValueException.java"));

    String pathTest = getPath(project.getFolder(), TEST_JAVA, "tech/jhipster/chips/error/domain");
    assertFileExist(getPath(pathTest, "AssertTest.java"));
    assertFileExist(getPath(pathTest, "MissingMandatoryValueExceptionTest.java"));
    assertFileExist(getPath(pathTest, "UnauthorizedValueExceptionTest.java"));
  }
}
