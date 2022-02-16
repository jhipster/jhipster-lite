package tech.jhipster.lite.generator.setup.codespace.application;

import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.TestUtils.tmpProject;
import static tech.jhipster.lite.generator.setup.codespace.application.CodespaceAssertFiles.assertFilesContainerJson;
import static tech.jhipster.lite.generator.setup.codespace.application.CodespaceAssertFiles.assertFilesDockerfile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
public class CodespaceApplicationServiceIT {

  @Autowired
  CodespaceApplicationService codespaceApplicationService;

  @Test
  void shouldInitFiles() {
    Project project = tmpProject();

    codespaceApplicationService.init(project);
    assertFilesDockerfile(project);
    assertFilesContainerJson(project);
  }
}
