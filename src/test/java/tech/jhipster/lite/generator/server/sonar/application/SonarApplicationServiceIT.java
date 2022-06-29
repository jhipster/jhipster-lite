package tech.jhipster.lite.generator.server.sonar.application;

import static tech.jhipster.lite.TestUtils.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.module.infrastructure.secondary.TestJHipsterModules;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class SonarApplicationServiceIT {

  @Autowired
  private SonarApplicationService sonarApplicationService;

  @Autowired
  private MavenApplicationService mavenApplicationService;

  @Test
  void shouldAddSonarJavaBackend() {
    Project project = tmpProject();
    TestJHipsterModules.applyInit(project);
    mavenApplicationService.addPomXml(project);

    sonarApplicationService.addSonarJavaBackend(project);

    SonarAssert.assertFiles(project);
    SonarAssert.assertPomXml(project);
  }

  @Test
  void shouldAddSonarJavaBackendAndFrontend() {
    Project project = tmpProject();
    TestJHipsterModules.applyInit(project);
    mavenApplicationService.addPomXml(project);

    sonarApplicationService.addSonarJavaBackendAndFrontend(project);

    SonarAssert.assertFiles(project);
    SonarAssert.assertFrontProperties(project);
    SonarAssert.assertPomXml(project);
  }
}
