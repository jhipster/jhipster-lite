package tech.jhipster.lite.generator.typescript.application;

import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.common.domain.WordUtils.DQ;
import static tech.jhipster.lite.generator.project.domain.Constants.PACKAGE_JSON;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class TypescriptApplicationServiceIT {

  @Autowired
  TypescriptApplicationService typescriptApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProjectWithPackageJsonComplete();

    typescriptApplicationService.init(project);

    assertFileContent(project, PACKAGE_JSON, "@types/jest" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@typescript-eslint/eslint-plugin" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "@typescript-eslint/parser" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "eslint" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "eslint-import-resolver-typescript" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "eslint-plugin-import" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "eslint-plugin-prettier" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "jest" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "ts-jest" + DQ + ": ");
    assertFileContent(project, PACKAGE_JSON, "typescript" + DQ + ": ");
  }
}
