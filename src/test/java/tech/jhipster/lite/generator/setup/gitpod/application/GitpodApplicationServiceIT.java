package tech.jhipster.lite.generator.setup.gitpod.application;

import static tech.jhipster.lite.TestUtils.tmpProject;
import static tech.jhipster.lite.generator.setup.gitpod.application.GitpodAssertFiles.assertFilesDockerfile;
import static tech.jhipster.lite.generator.setup.gitpod.application.GitpodAssertFiles.assertFilesGitpodYml;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class GitpodApplicationServiceIT {

  @Autowired
  GitpodApplicationService gitpodApplicationService;

  @Test
  void shouldInitFiles() {
    Project project = tmpProject();

    gitpodApplicationService.init(project);
    assertFilesDockerfile(project);
    assertFilesGitpodYml(project);
  }
}
