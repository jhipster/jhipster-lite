package tech.jhipster.forge.springboot.application;

import static tech.jhipster.forge.TestUtils.assertFileExist;
import static tech.jhipster.forge.common.domain.Constants.*;
import static tech.jhipster.forge.common.utils.FileUtils.getPath;

import tech.jhipster.forge.common.domain.Project;

public class LiquibaseAssertFiles {

  public static void assertFilesLiquibaseJava(Project project) {
    String liquibasePath = getPath(MAIN_JAVA, "com/mycompany/myapp/technical/secondary/liquibase");
    assertFileExist(project, getPath(liquibasePath, "AsyncSpringLiquibase.java"));
    assertFileExist(project, getPath(liquibasePath, "LiquibaseConfiguration.java"));
    assertFileExist(project, getPath(liquibasePath, "SpringLiquibaseUtil.java"));

    String liquibaseTestPath = getPath(TEST_JAVA, "com/mycompany/myapp/technical/secondary/liquibase");
    assertFileExist(project, getPath(liquibaseTestPath, "AsyncSpringLiquibaseTest.java"));
    assertFileExist(project, getPath(liquibaseTestPath, "LiquibaseConfigurationIT.java"));
    assertFileExist(project, getPath(liquibaseTestPath, "LogbackRecorder.java"));
    assertFileExist(project, getPath(liquibaseTestPath, "SpringLiquibaseUtilTest.java"));
  }

  public static void assertFilesLiquibaseChangelogMasterXml(Project project) {
    assertFileExist(project, getPath(MAIN_RESOURCES, "config/liquibase/master.xml"));
  }
}
