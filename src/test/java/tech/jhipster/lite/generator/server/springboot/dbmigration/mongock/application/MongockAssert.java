package tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.application;

import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.POM_XML;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;

import java.util.List;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.generator.project.domain.Project;

public class MongockAssert {

  public static void assertDependencies(Project project) {
    TestUtils.assertFileContent(project, POM_XML, "<mongock.version>");
    TestUtils.assertFileContent(project, POM_XML, "</mongock.version>");
    TestUtils.assertFileContent(
      project,
      POM_XML,
      List.of(
        "<dependency>",
        "<groupId>io.mongock</groupId>",
        "<artifactId>mongock-bom</artifactId>",
        "<version>${mongock.version}</version>",
        "<scope>import</scope>",
        "<type>pom</type>",
        "</dependency>"
      )
    );

    TestUtils.assertFileContent(
      project,
      POM_XML,
      List.of("<dependency>", "<groupId>io.mongock</groupId>", "<artifactId>mongock-springboot</artifactId>", "</dependency>")
    );

    TestUtils.assertFileContent(
      project,
      POM_XML,
      List.of("<dependency>", "<groupId>io.mongock</groupId>", "<artifactId>mongodb-springdata-v3-driver</artifactId>", "</dependency>")
    );
  }

  public static void assertConfigAndChangeLogFiles(Project project) {
    String mongockPath = getPath(
      MAIN_JAVA,
      project.getPackageNamePath().orElse("com/mycompany/myapp"),
      "technical/infrastructure/secondary/mongock"
    );
    assertFileExist(project, getPath(mongockPath, "MongockDatabaseConfiguration.java"));
    assertFileExist(project, getPath(mongockPath + "/dbmigration", "InitialMigrationSetup.java"));
  }

  public static void assertProperties(Project project) {
    TestUtils.assertFileContent(
      project,
      getPath(MAIN_RESOURCES, "config/application.properties"),
      List.of("mongock.migration-scan-package=tech.jhipster.chips.technical.infrastructure.secondary.mongock")
    );
    TestUtils.assertFileContent(project, getPath(TEST_RESOURCES, "config/application.properties"), List.of("mongock.enabled=false"));
  }
}
