package tech.jhipster.forge.generator.init.secondary;

import static tech.jhipster.forge.TestUtils.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.Project;

@UnitTest
@ExtendWith(SpringExtension.class)
class InitRepositoryTest {

  @InjectMocks
  InitRepository initRepository;

  @Test
  void shouldInitWithConfig() {
    Map<String, String> config = new HashMap<>(
      Map.of("baseName", "jhipsterForge", "projectName", "JHipster Forge", "prettierDefaultIndent", "4", "prettierJavaIndent", "4")
    );
    Project project = tmpProjectBuilder().config(config).build();

    initRepository.init(project);

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
    assertFileContent(project, ".prettierrc", "tabWidth: 4");

    assertFileExist(project, "package.json");
    assertFileContent(project, "package.json", "jhipster-forge");
  }

  @Test
  void shouldInitWithDefaultConfig() {
    Project project = tmpProject();

    initRepository.init(project);

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

    initRepository.addPackageJson(project);

    assertFileExist(project, "package.json");
  }

  @Test
  void shouldAddReadme() {
    Project project = tmpProject();

    initRepository.addReadme(project);

    assertFileExist(project, "README.md");
  }

  @Test
  void shouldAddGitConfiguration() {
    Project project = tmpProject();

    initRepository.addGitConfiguration(project);

    assertFileExist(project, ".gitignore");
    assertFileExist(project, ".gitattributes");
  }

  @Test
  void shouldAddEditorConfiguration() {
    Project project = tmpProject();

    initRepository.addEditorConfiguration(project);

    assertFileExist(project, ".editorconfig");
    assertFileExist(project, ".eslintignore");
  }

  @Test
  void shouldAddPrettier() {
    Project project = tmpProject();

    initRepository.addPrettier(project);

    assertFileExist(project, ".husky", "pre-commit");
    assertFileExist(project, ".lintstagedrc.js");
    assertFileExist(project, ".prettierignore");
    assertFileExist(project, ".prettierrc");
  }
}
