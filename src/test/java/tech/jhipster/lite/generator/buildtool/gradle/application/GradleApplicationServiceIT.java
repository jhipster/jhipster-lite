package tech.jhipster.lite.generator.buildtool.gradle.application;

import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.TestUtils.tmpProject;
import static tech.jhipster.lite.generator.buildtool.gradle.application.GradleAssertFiles.assertFilesGradle;
import static tech.jhipster.lite.generator.project.domain.Constants.BUILD_GRADLE_KTS;
import static tech.jhipster.lite.generator.project.domain.Constants.SETTINGS_GRADLE_KTS;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class GradleApplicationServiceIT {

  @Autowired
  GradleApplicationService gradleApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProject();

    gradleApplicationService.init(project);

    assertFilesGradle(project);
    assertFileContent(project, BUILD_GRADLE_KTS, "group = \"com.mycompany.myapp\"");
    assertFileContent(project, SETTINGS_GRADLE_KTS, "rootProject.name = \"jhipster\"");
  }
}
