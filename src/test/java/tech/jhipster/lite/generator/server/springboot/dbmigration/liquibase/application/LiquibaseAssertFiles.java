package tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.application;

import static org.mockito.Mockito.when;
import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.*;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import tech.jhipster.lite.generator.project.domain.DatabaseType;
import tech.jhipster.lite.generator.project.domain.Project;

public class LiquibaseAssertFiles {

  public static final String CURRENT_DATE = "2022-01-28T17:30:26.0Z";
  public static final ZoneId DEFAULT_TIMEZONE = ZoneId.of("UTC");

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
  }

  public static void assertFilesLiquibaseSqlUser(Project project, DatabaseType databaseType) {
    assertFileExist(project, getPath(MAIN_RESOURCES, "config/liquibase/changelog/20220128173026_added_entity_User.xml"));
    assertFileExist(project, getPath(MAIN_RESOURCES, "config/liquibase/data/user.csv"));
  }

  public static void assertFilesLiquibaseSqlUserAuthority(Project project, DatabaseType databaseType) {
    assertFileExist(project, getPath(MAIN_RESOURCES, "config/liquibase/changelog/20220128173026_added_entity_Authority.xml"));
    assertFileExist(project, getPath(MAIN_RESOURCES, "config/liquibase/data/authority.csv"));
    assertFileExist(project, getPath(MAIN_RESOURCES, "config/liquibase/data/user_authority.csv"));
  }

  public static void initClock(Clock clock) {
    when(clock.getZone()).thenReturn(DEFAULT_TIMEZONE);
    when(clock.instant()).thenReturn(Instant.parse(CURRENT_DATE));
  }
}
