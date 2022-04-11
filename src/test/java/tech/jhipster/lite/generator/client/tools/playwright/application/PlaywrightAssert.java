package tech.jhipster.lite.generator.client.tools.playwright.application;

import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.common.domain.WordUtils.DQ;
import static tech.jhipster.lite.generator.project.domain.Constants.PACKAGE_JSON;

import tech.jhipster.lite.generator.client.tools.playwright.domain.Playwright;
import tech.jhipster.lite.generator.project.domain.Project;

public class PlaywrightAssert {

  private PlaywrightAssert() {}

  public static void assertDependency(Project project) {
    Playwright.devDependencies().forEach(devDependency -> assertFileContent(project, PACKAGE_JSON, DQ + devDependency + DQ));
  }

  public static void assertPlaywrightScripts(Project project) {
    assertFileContent(project, PACKAGE_JSON, "e2e");
    assertFileContent(project, PACKAGE_JSON, "e2e:headless");
  }

  public static void assertPlaywrightTestFiles(Project project) {
    String pathIntegrationTestPrimaryApp = "src/test/javascript/integration/common/primary/app";

    assertFileExist(project, getPath(pathIntegrationTestPrimaryApp, "Home.spec.ts"));
  }

  public static void assertPlaywrightPageObjectFiles(Project project) {
    String pathIntegrationTestPrimaryApp = "src/test/javascript/integration/common/primary/app";

    assertFileExist(project, getPath(pathIntegrationTestPrimaryApp, "Home-Page.ts"));
  }
}
