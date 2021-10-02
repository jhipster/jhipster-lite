package tech.jhipster.forge.generator.springboot.primary;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tech.jhipster.forge.TestUtils.assertFileContent;
import static tech.jhipster.forge.TestUtils.assertFileExist;

import java.util.List;
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
class MavenResourceIT {

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
      .perform(post("/api/maven/init").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO)))
      .andExpect(status().isOk());

    String projectPath = projectDTO.getPath();
    assertFileExist(projectPath, "pom.xml");
    assertFileContent(projectPath, "pom.xml", List.of("<groupId>tech.jhipster.chips</groupId>", "<artifactId>chips</artifactId>"));
    assertFileExist(projectPath, "mvnw");
    assertFileExist(projectPath, "mvnw.cmd");
    assertFileExist(projectPath, ".mvn/wrapper/MavenWrapperDownloader.java");
    assertFileExist(projectPath, ".mvn/wrapper/maven-wrapper.jar");
    assertFileExist(projectPath, ".mvn/wrapper/maven-wrapper.properties");
  }

  @Test
  void shouldAddPomXml() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/maven.json", ProjectDTO.class);
    if (projectDTO == null) {
      throw new GeneratorException("Error when reading file");
    }
    projectDTO.path(FileUtils.tmpDirForTest());

    mockMvc
      .perform(post("/api/maven/pom-xml").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO)))
      .andExpect(status().isOk());

    String projectPath = projectDTO.getPath();

    assertFileExist(projectPath, "pom.xml");
    assertFileContent(projectPath, "pom.xml", List.of("<groupId>tech.jhipster.chips</groupId>", "<artifactId>chips</artifactId>"));
  }

  @Test
  void shouldAddMavenWrapper() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/maven.json", ProjectDTO.class);
    if (projectDTO == null) {
      throw new GeneratorException("Error when reading file");
    }
    projectDTO.path(FileUtils.tmpDirForTest());

    mockMvc
      .perform(post("/api/maven/wrapper").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO)))
      .andExpect(status().isOk());

    String projectPath = projectDTO.getPath();

    assertFileExist(projectPath, "mvnw");
    assertFileExist(projectPath, "mvnw.cmd");
    assertFileExist(projectPath, ".mvn/wrapper/MavenWrapperDownloader.java");
    assertFileExist(projectPath, ".mvn/wrapper/maven-wrapper.jar");
    assertFileExist(projectPath, ".mvn/wrapper/maven-wrapper.properties");
  }
}
