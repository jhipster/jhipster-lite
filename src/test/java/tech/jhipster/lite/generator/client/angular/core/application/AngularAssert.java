package tech.jhipster.lite.generator.client.angular.core.application;

import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.common.domain.WordUtils.DQ;
import static tech.jhipster.lite.generator.project.domain.Constants.PACKAGE_JSON;

import tech.jhipster.lite.generator.project.domain.Project;

public class AngularAssert {

  private AngularAssert() {}

  public static void assertDevDependencies(Project project) {
    assertFileContent(project, PACKAGE_JSON, "@angular-builders/jest" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@angular-devkit/build-angular" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@angular/cli" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@angular/compiler-cli" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@types/node" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@types/jest" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "jest" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "ts-jest" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "jest-preset-angular" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "jest-sonar-reporter" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "typescript" + DQ + ": ");
  }

  public static void assertDependencies(Project project) {
    assertFileContent(project, PACKAGE_JSON, "@angular/animations" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@angular/common" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@angular/compiler" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@angular/core" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@angular/forms" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@angular/platform-browser" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@angular/platform-browser-dynamic" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@angular/router" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "rxjs" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "tslib" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "zone.js" + DQ + ": ");
  }

  public static void assertScripts(Project project) {
    assertFileContent(project, PACKAGE_JSON, "ng");
    assertFileContent(project, PACKAGE_JSON, "start");
    assertFileContent(project, PACKAGE_JSON, "build");
    assertFileContent(project, PACKAGE_JSON, "watch");
    assertFileContent(project, PACKAGE_JSON, "test");
  }

  public static void assertConfigFiles(Project project) {
    assertFileExist(project, "angular.json");
    assertFileExist(project, "jest.conf.js");
    assertFileExist(project, "tsconfig.app.json");
    assertFileExist(project, "tsconfig.json");
    assertFileExist(project, "tsconfig.spec.json");
  }

  public static void assertAngularFiles(Project project) {
    String pathWebapp = "src/main/webapp";
    String pathApp = "src/main/webapp/app";
    String pathEnvironments = "src/main/webapp/environments";

    assertFileExist(project, getPath(pathApp, "app.component.css"));
    assertFileExist(project, getPath(pathApp, "app.component.html"));
    assertFileExist(project, getPath(pathApp, "app.component.spec.ts"));
    assertFileExist(project, getPath(pathApp, "app.component.ts"));
    assertFileExist(project, getPath(pathApp, "app.module.ts"));
    assertFileExist(project, getPath(pathApp, "app-routing.module.ts"));
    assertFileExist(project, getPath(pathWebapp, "index.html"));
    assertFileExist(project, getPath(pathWebapp, "main.ts"));
    assertFileExist(project, getPath(pathWebapp, "polyfills.ts"));
    assertFileExist(project, getPath(pathWebapp, "styles.css"));
    assertFileExist(project, getPath(pathEnvironments, "environment.prod.ts"));
    assertFileExist(project, getPath(pathEnvironments, "environment.ts"));
  }
}
