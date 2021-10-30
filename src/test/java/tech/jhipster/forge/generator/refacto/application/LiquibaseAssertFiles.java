package tech.jhipster.forge.generator.refacto.application;

import static tech.jhipster.forge.TestUtils.assertFileExist;
import static tech.jhipster.forge.generator.refacto.domain.core.Constants.*;
import static tech.jhipster.forge.generator.refacto.domain.core.FileUtils.getPath;

import tech.jhipster.forge.generator.refacto.domain.core.Project;

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
