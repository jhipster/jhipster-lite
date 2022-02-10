package tech.jhipster.lite.generator.setup.codespace.application;

import static tech.jhipster.lite.TestUtils.assertFileExist;

import tech.jhipster.lite.generator.project.domain.Project;

public class CodespaceAssertFiles {

  public static void assertFilesContainerJson(Project project) {
    assertFileExist(project, ".devcontainer/devcontainer.json");
  }

  public static void assertFilesDockerfile(Project project) {
    assertFileExist(project, ".devcontainer/Dockerfile");
  }
}
