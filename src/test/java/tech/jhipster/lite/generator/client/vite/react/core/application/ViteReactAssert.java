package tech.jhipster.lite.generator.client.vite.react.core.application;

import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.common.domain.WordUtils.DQ;
import static tech.jhipster.lite.generator.project.domain.Constants.PACKAGE_JSON;

import tech.jhipster.lite.generator.project.domain.Project;

public class ViteReactAssert {

  private ViteReactAssert() {}

  public static void assertDependencies(Project project) {
    assertFileContent(project, PACKAGE_JSON, "@testing-library/jest-dom" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@testing-library/react" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@testing-library/user-event" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@types/jest" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@types/node" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@types/react" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@types/react-dom" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@vitejs/plugin-react" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "jest" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "jest-css-modules" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "react" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "react-dom" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "ts-jest" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "ts-node" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "typescript" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "vite" + DQ + ": ");
  }

  public static void assertScripts(Project project) {
    assertFileContent(project, PACKAGE_JSON, "dev");
    assertFileContent(project, PACKAGE_JSON, "build");
    assertFileContent(project, PACKAGE_JSON, "preview");
    assertFileContent(project, PACKAGE_JSON, "test");
  }

  public static void assertFiles(Project project) {
    assertFileExist(project, "tsconfig.json");
    assertFileExist(project, "vite.config.ts");
    assertFileExist(project, "jest.config.ts");
  }

  public static void assertReactFiles(Project project) {
    String pathSrc = "src";
    String pathWebapp = "src/main/webapp";
    String pathApp = "src/main/webapp/app";
    String primaryApp = "src/main/webapp/app/common/primary/app";
    String primaryTestApp = "src/test/javascript/spec/common/primary/app";

    assertFileExist(project, getPath(pathWebapp, "index.html"));
    assertFileExist(project, getPath(pathApp, "index.css"));
    assertFileExist(project, getPath(pathApp, "index.tsx"));
    assertFileExist(project, getPath(pathApp, "vite-env.d.ts"));
    assertFileExist(project, getPath(primaryApp, "App.css"));
    assertFileExist(project, getPath(primaryApp, "App.tsx"));
    assertFileExist(project, getPath(primaryTestApp, "App.test.tsx"));
    assertFileExist(project, getPath(pathSrc, "setupTests.ts"));
  }
}
