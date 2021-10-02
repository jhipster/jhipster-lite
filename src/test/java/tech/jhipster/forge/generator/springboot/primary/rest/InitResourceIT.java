package tech.jhipster.forge.generator.springboot.primary.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tech.jhipster.forge.TestUtils.assertFileContent;
import static tech.jhipster.forge.TestUtils.assertFileExist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.forge.IntegrationTest;
import tech.jhipster.forge.TestUtils;
import tech.jhipster.forge.common.utils.FileUtils;
import tech.jhipster.forge.generator.springboot.primary.rest.ProjectDTO;

@IntegrationTest
@AutoConfigureMockMvc
class InitResourceIT {

  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldInit() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/init.json", ProjectDTO.class).path(FileUtils.tmpDirForTest());

    mockMvc
      .perform(post("/api/init").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO)))
      .andExpect(status().isOk());

    String projectPath = projectDTO.getPath();

    assertFileExist(projectPath, "README.md");
    assertFileContent(projectPath, "README.md", "Chips Project");

    assertFileExist(projectPath, ".gitignore");
    assertFileExist(projectPath, ".gitattributes");

    assertFileExist(projectPath, ".editorconfig");
    assertFileExist(projectPath, ".eslintignore");

    assertFileExist(projectPath, ".husky", "pre-commit");
    assertFileExist(projectPath, ".lintstagedrc.js");
    assertFileExist(projectPath, ".prettierignore");
    assertFileExist(projectPath, ".prettierrc");
    assertFileContent(projectPath, ".prettierrc", "tabWidth: 4");

    assertFileExist(projectPath, "package.json");
    assertFileContent(projectPath, "package.json", "chips");
  }
}
