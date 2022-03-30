package tech.jhipster.lite.generator.init.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.common.domain.WordUtils.CRLF;
import static tech.jhipster.lite.generator.init.application.InitAssertFiles.*;
import static tech.jhipster.lite.generator.project.domain.Constants.PACKAGE_JSON;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmRepository;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class InitApplicationServiceIT {

  @Autowired
  InitApplicationService initApplicationService;

  @SpyBean
  NpmRepository npmRepository;

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
    Project project = tmpProjectBuilder().endOfLine(CRLF).config(config).build();

    initApplicationService.init(project);

    assertFilesInit(project);
    assertFileContent(project, "README.md", "JHipster Lite");
    assertFileContent(project, PACKAGE_JSON, "jhipster-lite");
    assertFileContent(project, ".editorconfig", "end_of_line = crlf");
    assertFileContent(project, ".prettierrc", "endOfLine: \"crlf\"");
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
    assertFileGitInit(project);
  }

  @Test
  void shouldInitWithDefaultConfig() {
    Project project = tmpProject();

    initApplicationService.init(project);

    assertFilesInit(project);
    assertFileContent(project, "README.md", "JHipster Project");
    assertFileContent(project, PACKAGE_JSON, "jhipster");
    assertFileContent(project, ".editorconfig", "end_of_line = lf");
    assertFileContent(project, ".prettierrc", "endOfLine: \"lf\"");
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
    List
      .of("@prettier/plugin-xml", "husky", "lint-staged", "prettier", "prettier-plugin-java", "prettier-plugin-packagejson")
      .forEach(dependency -> assertFileContent(project, PACKAGE_JSON, dependency));
    List.of("prepare", "prettier:check", "prettier:format").forEach(dependency -> assertFileContent(project, PACKAGE_JSON, dependency));
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

    assertFilesGitConfiguration(project);
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

  @Test
  void shouldGitInit() {
    Project project = tmpProject();

    initApplicationService.gitInit(project);

    assertFileGitInit(project);
  }

  @Test
  void shouldDownloadProject() {
    Project project = tmpProjectWithPomXml();

    assertThat(initApplicationService.download(project)).isNotNull();
  }
}
