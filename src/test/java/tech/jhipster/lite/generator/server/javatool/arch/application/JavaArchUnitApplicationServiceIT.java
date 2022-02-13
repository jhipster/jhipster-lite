package tech.jhipster.lite.generator.server.javatool.arch.application;

import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.generator.server.javatool.arch.application.JavaArchUnitAssertFiles.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class JavaArchUnitApplicationServiceIT {

  @Autowired
  JavaArchUnitApplicationService javaArchUnitApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProjectWithPomXml();

    javaArchUnitApplicationService.init(project);
    assertArchUnitMavenPlugin(project);
    assertFilesAnnotations(project);
    assertFilesHexaArchTest(project);
  }
}
