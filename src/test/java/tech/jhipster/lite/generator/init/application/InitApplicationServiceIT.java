package tech.jhipster.lite.generator.init.application;

import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.generator.init.application.InitAssertFiles.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class InitApplicationServiceIT {

  @Autowired
  InitApplicationService initApplicationService;

  @Test
  void shouldInitWithConfig() {
    // @formatter:off
    Map<String, Object> config = new HashMap<>(
      Map.of(
        BASE_NAME, "jhipsterLite",
        PROJECT_NAME, "JHipster Lite",
        PRETTIER_DEFAULT_INDENT, 4
      )
    );
    // @formatter:on
    Project project = tmpProjectBuilder().config(config).build();

    initApplicationService.init(project);

    assertFilesInit(project);
    assertFileContent(project, "README.md", "JHipster Lite");
    assertFileContent(project, "package.json", "jhipster-lite");
    assertFileContent(project, ".prettierrc", "tabWidth: 4");
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
  }

  @Test
  void shouldInitWithDefaultConfig() {
    Project project = tmpProject();

    initApplicationService.init(project);

    assertFilesInit(project);
    assertFileContent(project, "README.md", "JHipster Project");
    assertFileContent(project, "package.json", "jhipster");
    assertFileContent(project, ".prettierrc", "tabWidth: 2");
    // @formatter:off
    assertFileContent(project, ".prettierrc",
      List.of(
        "overrides:",
        "- files: \"*.java\"",
        "options:",
        "tabWidth: 2"
      )
    );
    // @formatter:on
  }

  @Test
  void shouldAddPackageJson() {
    Project project = tmpProject();

    initApplicationService.addPackageJson(project);

    assertFilesPackageJson(project);
  }

  @Test
  void shouldAddReadme() {
    Project project = tmpProject();

    initApplicationService.addReadme(project);

    assertFilesReadme(project);
  }

  @Test
  void shouldAddGitConfiguration() {
    Project project = tmpProject();

    initApplicationService.addGitConfiguration(project);

    assertFilesConfiguration(project);
  }

  @Test
  void shouldAddEditorConfiguration() {
    Project project = tmpProject();

    initApplicationService.addEditorConfiguration(project);

    assertFilesEditorConfiguration(project);
  }

  @Test
  void shouldAddPrettier() {
    Project project = tmpProject();

    initApplicationService.addPrettier(project);

    assertFilesPrettier(project);
  }
}
