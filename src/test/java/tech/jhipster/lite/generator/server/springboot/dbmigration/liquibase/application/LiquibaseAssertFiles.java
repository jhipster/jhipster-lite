package tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.application;

import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.*;

import tech.jhipster.lite.generator.project.domain.Project;

public class LiquibaseAssertFiles {

  public static void assertFilesLiquibaseJava(Project project) {
    String liquibasePackage = project.getPackageName().orElse("com.mycompany.myapp") + ".technical.infrastructure.secondary.liquibase";
    String liquibasePath = getPath(
      MAIN_JAVA,
      project.getPackageNamePath().orElse("com/mycompany/myapp"),
      "technical/infrastructure/secondary/liquibase"
    );
    assertFileExist(project, getPath(liquibasePath, "AsyncSpringLiquibase.java"));
    assertFileExist(project, getPath(liquibasePath, "LiquibaseConfiguration.java"));
    assertFileExist(project, getPath(liquibasePath, "SpringLiquibaseUtil.java"));

    assertFileContent(project, getPath(liquibasePath, "AsyncSpringLiquibase.java"), "package " + liquibasePackage);
    assertFileContent(project, getPath(liquibasePath, "LiquibaseConfiguration.java"), "package " + liquibasePackage);
    assertFileContent(project, getPath(liquibasePath, "SpringLiquibaseUtil.java"), "package " + liquibasePackage);

    assertFileExist(project, getPath(TEST_JAVA, project.getPackageNamePath().orElse("com/mycompany/myapp"), "LogbackRecorder.java"));

    String liquibaseTestPath = getPath(
      TEST_JAVA,
      project.getPackageNamePath().orElse("com/mycompany/myapp"),
      "technical/infrastructure/secondary/liquibase"
    );
    assertFileExist(project, getPath(liquibaseTestPath, "AsyncSpringLiquibaseTest.java"));
    assertFileExist(project, getPath(liquibaseTestPath, "LiquibaseConfigurationIT.java"));
    assertFileExist(project, getPath(liquibaseTestPath, "SpringLiquibaseUtilTest.java"));

    assertFileContent(project, getPath(liquibaseTestPath, "AsyncSpringLiquibaseTest.java"), "package " + liquibasePackage);
    assertFileContent(project, getPath(liquibaseTestPath, "LiquibaseConfigurationIT.java"), "package " + liquibasePackage);
    assertFileContent(project, getPath(liquibaseTestPath, "SpringLiquibaseUtilTest.java"), "package " + liquibasePackage);
  }

  public static void assertFilesLiquibaseChangelogMasterXml(Project project) {
    assertFileExist(project, getPath(MAIN_RESOURCES, "config/liquibase/master.xml"));
    assertFileExist(project, getPath(MAIN_RESOURCES, "config/liquibase/changelog/sequence.xml"));
    assertFileExist(project, getPath(MAIN_RESOURCES, "config/liquibase/changelog/date_time_wrapper.xml"));
  }

  public static void assertFilesLiquibaseSqlUser(Project project, String databaseName) {
    assertFileExist(project, getPath(MAIN_RESOURCES, "config/liquibase/changelog/user", databaseName, "user.xml"));
    assertFileExist(project, getPath(MAIN_RESOURCES, "config/liquibase/changelog/user", databaseName, "user.csv"));
  }

  public static void assertFilesLiquibaseSqlUserAuthority(Project project, String databaseName) {
    assertFileExist(project, getPath(MAIN_RESOURCES, "config/liquibase/changelog/user", databaseName, "authority.xml"));
    assertFileExist(project, getPath(MAIN_RESOURCES, "config/liquibase/changelog/user", databaseName, "authority.csv"));
    assertFileExist(project, getPath(MAIN_RESOURCES, "config/liquibase/changelog/user", databaseName, "user_authority.csv"));
  }
}
