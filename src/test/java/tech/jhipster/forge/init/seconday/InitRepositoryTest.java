package tech.jhipster.forge.init.seconday;

import static tech.jhipster.forge.TestUtils.assertFileExist;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.FileUtils;
import tech.jhipster.forge.common.domain.Project;

@UnitTest
@ExtendWith(SpringExtension.class)
class InitRepositoryTest {

  @InjectMocks
  InitRepository initRepository;

  @Test
  void shouldInitWithConfig() {
    String path = FileUtils.tmpDirForTest();
    Map<String, String> config = new HashMap<>(
      Map.of("baseName", "jhipsterForge", "projectName", "JHipster Forge", "prettierDefaultIndent", "4", "prettierJavaIndent", "4")
    );
    Project project = Project.builder().path(path).config(config).build();

    initRepository.init(project);

    assertFileExist(project, "README.md");

    assertFileExist(project, ".gitignore");
    assertFileExist(project, ".gitattributes");

    assertFileExist(project, ".editorconfig");
    assertFileExist(project, ".eslintignore");

    assertFileExist(project, ".huskyrc");
    assertFileExist(project, ".lintstagedrc.js");
    assertFileExist(project, ".prettierignore");
    assertFileExist(project, ".prettierrc");

    assertFileExist(project, "package.json");
  }

  @Test
  void shouldInitWithDefaultConfig() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();

    initRepository.init(project);

    assertFileExist(project, "README.md");

    assertFileExist(project, ".gitignore");
    assertFileExist(project, ".gitattributes");

    assertFileExist(project, ".editorconfig");
    assertFileExist(project, ".eslintignore");

    assertFileExist(project, ".huskyrc");
    assertFileExist(project, ".lintstagedrc.js");
    assertFileExist(project, ".prettierignore");
    assertFileExist(project, ".prettierrc");

    assertFileExist(project, "package.json");
  }

  @Test
  void shouldAddPackageJson() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();

    initRepository.addPackageJson(project);

    assertFileExist(project, "package.json");
  }

  @Test
  void shouldAddReadme() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();

    initRepository.addReadme(project);

    assertFileExist(project, "README.md");
  }

  @Test
  void shouldAddGitConfiguration() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();

    initRepository.addGitConfiguration(project);

    assertFileExist(project, ".gitignore");
    assertFileExist(project, ".gitattributes");
  }

  @Test
  void shouldAddEditorConfiguration() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();

    initRepository.addEditorConfiguration(project);

    assertFileExist(project, ".editorconfig");
    assertFileExist(project, ".eslintignore");
  }

  @Test
  void shouldAddPrettier() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();

    initRepository.addPrettier(project);

    assertFileExist(project, ".huskyrc");
    assertFileExist(project, ".lintstagedrc.js");
    assertFileExist(project, ".prettierignore");
    assertFileExist(project, ".prettierrc");
  }
}
