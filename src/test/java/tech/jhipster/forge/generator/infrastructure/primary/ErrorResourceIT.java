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
import tech.jhipster.forge.common.utils.FileUtils;
import tech.jhipster.forge.error.domain.GeneratorException;

@IntegrationTest
@AutoConfigureMockMvc
class ErrorResourceIT {

  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldInit() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/maven.json", ProjectDTO.class);
    if (projectDTO == null) {
      throw new GeneratorException("Error when reading file");
    }
    projectDTO.path(FileUtils.tmpDirForTest());

    mockMvc
      .perform(post("/api/error").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO)))
      .andExpect(status().isOk());

    String pathMain = getPath(projectDTO.getPath(), MAIN_JAVA, "tech/jhipster/chips/error/domain");
    assertFileExist(getPath(pathMain, "Assert.java"));
    assertFileExist(getPath(pathMain, "MissingMandatoryValueException.java"));
    assertFileExist(getPath(pathMain, "UnauthorizedValueException.java"));

    String pathTest = getPath(projectDTO.getPath(), TEST_JAVA, "tech/jhipster/chips/error/domain");
    assertFileExist(getPath(pathTest, "AssertTest.java"));
    assertFileExist(getPath(pathTest, "MissingMandatoryValueExceptionTest.java"));
    assertFileExist(getPath(pathTest, "UnauthorizedValueExceptionTest.java"));
  }
}
