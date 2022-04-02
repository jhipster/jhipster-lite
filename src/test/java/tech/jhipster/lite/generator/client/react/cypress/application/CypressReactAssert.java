package tech.jhipster.lite.generator.client.react.cypress.application;

import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.common.domain.WordUtils.DQ;
import static tech.jhipster.lite.generator.project.domain.Constants.PACKAGE_JSON;

import tech.jhipster.lite.generator.client.react.cypress.domain.CypressReact;
import tech.jhipster.lite.generator.project.domain.Project;

public class CypressReactAssert {

  private CypressReactAssert() {}

  public static void assertDependency(Project project) {
    CypressReact.devDependencies().forEach(devDependency -> assertFileContent(project, PACKAGE_JSON, DQ + devDependency + DQ));
  }

  public static void assertCypressScripts(Project project) {
    assertFileContent(project, PACKAGE_JSON, "cypress:open");
    assertFileContent(project, PACKAGE_JSON, "cypress:run");
  }

  public static void assertCypressFiles(Project project) {
    String pathRoot = "";
    String pathPlugins = "cypress/plugins";
    String pathTestConfig = "src/test/javascript/integration";

    assertFileExist(project, getPath(pathRoot, "cypress.json"));
    assertFileExist(project, getPath(pathPlugins, "index.ts"));
    assertFileExist(project, getPath(pathTestConfig, "tsconfig.json"));
  }

  public static void assertCypressTestFiles(Project project) {
    String pathIntegrationTestPrimaryApp = "src/test/javascript/integration/common/primary/app";

    assertFileExist(project, getPath(pathIntegrationTestPrimaryApp, "App.test.tsx"));
  }
}
