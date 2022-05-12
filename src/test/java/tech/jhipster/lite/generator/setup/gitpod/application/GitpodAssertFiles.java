package tech.jhipster.lite.generator.setup.gitpod.application;

import static tech.jhipster.lite.TestUtils.assertFileExist;

import tech.jhipster.lite.generator.project.domain.Project;

public class GitpodAssertFiles {

  private GitpodAssertFiles() {}

  public static void assertFilesGitpodYml(Project project) {
    assertFileExist(project, ".gitpod.yml");
  }

  public static void assertFilesDockerfile(Project project) {
    assertFileExist(project, ".gitpod.Dockerfile");
  }
}
