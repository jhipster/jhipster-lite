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
  void shouldAddJsonContainer() {
    Project project = tmpProject();

    codespaceApplicationService.addJSONcontainer(project);

    assertFilesContainerJson(project);
    assertFileContent(project, ".devcontainer/devcontainer.json", "\"name\": \"Java\"");
  }

  @Test
  void shouldAddDockerfile() {
    Project project = tmpProject();

    codespaceApplicationService.addDockerfilecontainer(project);

    assertFilesDockerfile(project);
    assertFileContent(project, ".devcontainer/Dockerfile", "ARG VARIANT=\"17\"");
  }
}
