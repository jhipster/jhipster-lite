package tech.jhipster.lite.generator.client.tools.playwright.application;

import static tech.jhipster.lite.TestUtils.tmpProjectWithPackageJsonComplete;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.client.react.core.application.ReactApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class PlaywrightApplicationServiceIT {

  @Autowired
  PlaywrightApplicationService playwrightApplicationService;

  @Autowired
  ReactApplicationService reactApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProjectWithPackageJsonComplete();
    reactApplicationService.addReact(project);

    playwrightApplicationService.init(project);

    PlaywrightAssert.assertDependency(project);
    PlaywrightAssert.assertPlaywrightScripts(project);
    PlaywrightAssert.assertPlaywrightTestFiles(project);
    PlaywrightAssert.assertPlaywrightPageObjectFiles(project);
  }
}
