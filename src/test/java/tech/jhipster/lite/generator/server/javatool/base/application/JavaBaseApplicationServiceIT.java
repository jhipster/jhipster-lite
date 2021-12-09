package tech.jhipster.lite.generator.server.javatool.base.application;

import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class JavaBaseApplicationServiceIT {

  @Autowired
  JavaBaseApplicationService javaBaseApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProject();

    javaBaseApplicationService.init(project);

    String pathMain = "src/main/java/com/mycompany/myapp/error/domain";
    assertFileExist(project, getPath(pathMain, "Assert.java"));
    assertFileExist(project, getPath(pathMain, "MissingMandatoryValueException.java"));
    assertFileExist(project, getPath(pathMain, "UnauthorizedValueException.java"));

    assertFileExist(project, getPath("src/test/java/com/mycompany/myapp", "UnitTest.java"));
    assertFileExist(project, getPath("src/test/java/com/mycompany/myapp", "ReplaceCamelCase.java"));

    String pathTest = "src/test/java/com/mycompany/myapp/error/domain";
    assertFileExist(project, getPath(pathTest, "AssertTest.java"));
    assertFileExist(project, getPath(pathTest, "MissingMandatoryValueExceptionTest.java"));
    assertFileExist(project, getPath(pathTest, "UnauthorizedValueExceptionTest.java"));
  }

  @Test
  void shouldInitWithSpecificPackage() {
    Project project = tmpProject();
    project.addConfig(PACKAGE_NAME, "tech.jhipster.chips");

    javaBaseApplicationService.init(project);

    String packageResult = "package tech.jhipster.chips.error.domain;";

    String pathMain = "src/main/java/tech/jhipster/chips/error/domain";
    assertFileExist(project, getPath(pathMain, "Assert.java"));
    assertFileContent(project, getPath(pathMain, "Assert.java"), packageResult);

    assertFileExist(project, getPath(pathMain, "MissingMandatoryValueException.java"));
    assertFileContent(project, getPath(pathMain, "MissingMandatoryValueException.java"), packageResult);

    assertFileExist(project, getPath(pathMain, "UnauthorizedValueException.java"));
    assertFileContent(project, getPath(pathMain, "UnauthorizedValueException.java"), packageResult);

    assertFileExist(project, getPath("src/test/java/tech/jhipster/chips", "UnitTest.java"));
    assertFileExist(project, getPath("src/test/java/tech/jhipster/chips", "ReplaceCamelCase.java"));

    String pathTest = "src/test/java/tech/jhipster/chips/error/domain";
    assertFileExist(project, getPath(pathTest, "AssertTest.java"));
    assertFileContent(project, getPath(pathTest, "AssertTest.java"), packageResult);

    assertFileExist(project, getPath(pathTest, "MissingMandatoryValueExceptionTest.java"));
    assertFileContent(project, getPath(pathTest, "MissingMandatoryValueExceptionTest.java"), packageResult);

    assertFileExist(project, getPath(pathTest, "UnauthorizedValueExceptionTest.java"));
    assertFileContent(project, getPath(pathTest, "UnauthorizedValueExceptionTest.java"), packageResult);
  }
}
