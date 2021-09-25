package tech.jhipster.forge.generator.init.application;

import static tech.jhipster.forge.TestUtils.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.forge.IntegrationTest;
import tech.jhipster.forge.common.domain.Project;

@IntegrationTest
class InitApplicationServiceIT {

  @Autowired
  InitApplicationService initApplicationService;

  @Test
  void shouldInitWithConfig() {
    // @formatter:off
    Map<String, String> config = new HashMap<>(
      Map.of(
        "baseName", "jhipsterForge",
        "projectName", "JHipster Forge",
        "prettierDefaultIndent", "2",
        "prettierJavaIndent", "4"
      )
    );
    // @formatter:on
    Project project = tmpProjectBuilder().config(config).build();

    initApplicationService.init(project);

    assertFileExist(project, "README.md");
    assertFileContent(project, "README.md", "JHipster Forge");

    assertFileExist(project, ".gitignore");
    assertFileExist(project, ".gitattributes");

    assertFileExist(project, ".editorconfig");
    assertFileExist(project, ".eslintignore");

    assertFileExist(project, ".husky", "pre-commit");
    assertFileExist(project, ".lintstagedrc.js");
    assertFileExist(project, ".prettierignore");
    assertFileExist(project, ".prettierrc");
    assertFileContent(project, ".prettierrc", "tabWidth: 2");
    // @formatter:off
    assertFileContent(project, ".prettierrc",
      List.of(
        "overrides:",
        "- files: \"*.java\"",
        "options:",
        "tabWidth: 4"
      )
    );
    // @formatter:on

    assertFileExist(project, "package.json");
    assertFileContent(project, "package.json", "jhipster-forge");
  }

  @Test
  void shouldInitWithDefaultConfig() {
    Project project = tmpProject();

    initApplicationService.init(project);

    assertFileExist(project, "README.md");
    assertFileContent(project, "README.md", "JHipster Project");

    assertFileExist(project, ".gitignore");
    assertFileExist(project, ".gitattributes");

    assertFileExist(project, ".editorconfig");
    assertFileExist(project, ".eslintignore");

    assertFileExist(project, ".husky", "pre-commit");
    assertFileExist(project, ".lintstagedrc.js");
    assertFileExist(project, ".prettierignore");
    assertFileExist(project, ".prettierrc");
    assertFileContent(project, ".prettierrc", "tabWidth: 2");

    assertFileExist(project, "package.json");
    assertFileContent(project, "package.json", "jhipster");
  }

  @Test
  void shouldAddPackageJson() {
    Project project = tmpProject();

    initApplicationService.addPackageJson(project);

    assertFileExist(project, "package.json");
  }

  @Test
  void shouldAddReadme() {
    Project project = tmpProject();

    initApplicationService.addReadme(project);

    assertFileExist(project, "README.md");
  }

  @Test
  void shouldAddGitConfiguration() {
    Project project = tmpProject();

    initApplicationService.addGitConfiguration(project);

    assertFileExist(project, ".gitignore");
    assertFileExist(project, ".gitattributes");
  }

  @Test
  void shouldAddEditorConfiguration() {
    Project project = tmpProject();

    initApplicationService.addEditorConfiguration(project);

    assertFileExist(project, ".editorconfig");
    assertFileExist(project, ".eslintignore");
  }

  @Test
  void shouldAddPrettier() {
    Project project = tmpProject();

    initApplicationService.addPrettier(project);

    assertFileExist(project, ".husky", "pre-commit");
    assertFileExist(project, ".lintstagedrc.js");
    assertFileExist(project, ".prettierignore");
    assertFileExist(project, ".prettierrc");
  }
}
