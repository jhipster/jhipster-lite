package tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.buildtool.maven.domain.MavenDomainService.POM_XML;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.LOGGING_CONFIGURATION;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.LOGGING_TEST_CONFIGURATION;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.application.LiquibaseAssertFiles.assertFilesLiquibaseChangelogMasterXml;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.application.LiquibaseAssertFiles.assertFilesLiquibaseJava;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.BuildToolType;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.core.domain.SpringBootService;
import tech.jhipster.lite.generator.server.springboot.database.postgresql.domain.PostgresqlService;

@IntegrationTest
class LiquibaseApplicationServiceIT {

  @Autowired
  BuildToolService buildToolService;

  @Autowired
  SpringBootService springBootService;

  @Autowired
  PostgresqlService postgresqlService;

  @Autowired
  LiquibaseApplicationService liquibaseApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProjectBuilder().build();
    buildToolService.init(project, BuildToolType.MAVEN);
    springBootService.init(project);
    postgresqlService.init(project);

    liquibaseApplicationService.init(project);

    assertFileContent(
      project,
      POM_XML,
      List.of("<dependency>", "<groupId>org.liquibase</groupId>", "<artifactId>liquibase-core</artifactId>", "</dependency>")
    );
    assertFileContent(
      project,
      POM_XML,
      List.of("<dependency>", "<groupId>com.h2database</groupId>", "<artifactId>h2</artifactId>", "<scope>test</scope>", "</dependency>")
    );
    assertFilesLiquibaseChangelogMasterXml(project);
    assertFilesLiquibaseJava(project);
    assertLoggerInConfig(project);
  }

  @Test
  void shouldAddLiquibase() {
    Project project = tmpProjectBuilder().build();
    buildToolService.init(project, BuildToolType.MAVEN);

    liquibaseApplicationService.addLiquibase(project);

    assertFileContent(
      project,
      POM_XML,
      List.of("<dependency>", "<groupId>org.liquibase</groupId>", "<artifactId>liquibase-core</artifactId>", "</dependency>")
    );
  }

  @Test
  void shouldAddChangelogMasterXml() {
    Project project = tmpProject();

    liquibaseApplicationService.addChangelogMasterXml(project);

    assertFilesLiquibaseChangelogMasterXml(project);
  }

  @Test
  void shouldAddChangelog() {
    Project project = tmpProject();

    liquibaseApplicationService.addChangelogMasterXml(project);
    liquibaseApplicationService.addChangelogXml(project, "v1", "master.xml");

    String expected = "  <include file=\"config/liquibase/changelog/v1/master.xml\" relativeToChangelogFile=\"false\"/>";

    assertFileContent(project, getPath(MAIN_RESOURCES, "config/liquibase/master.xml"), expected);
  }

  @Test
  void shouldAddChangelogOnlyOneTime() throws Exception {
    Project project = tmpProject();

    liquibaseApplicationService.addChangelogMasterXml(project);
    liquibaseApplicationService.addChangelogXml(project, "v1", "master.xml");
    liquibaseApplicationService.addChangelogXml(project, "v1", "master.xml");

    String expected = "  <include file=\"config/liquibase/changelog/v1/master.xml\" relativeToChangelogFile=\"false\"/>";

    assertFileContentManyTimes(project, getPath(MAIN_RESOURCES, "config/liquibase/master.xml"), expected, 1);
  }

  @Test
  void shouldNotAddChangelogWhenNoMasterXml() throws Exception {
    Project project = tmpProject();

    assertThatThrownBy(() -> liquibaseApplicationService.addChangelogXml(project, "v1", "master.xml"))
      .isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldAddConfigurationJava() {
    Project project = tmpProject();

    liquibaseApplicationService.addConfigurationJava(project);

    assertFilesLiquibaseJava(project);
  }

  @Test
  void shouldAddConfigurationJavaWithPackageName() {
    Project project = tmpProject();
    project.addConfig(PACKAGE_NAME, "tech.jhipster.lite");

    liquibaseApplicationService.addConfigurationJava(project);

    assertFilesLiquibaseJava(project);
  }

  @Test
  void shouldAddLoggingConfiguration() {
    Project project = tmpProject();
    buildToolService.init(project, BuildToolType.MAVEN);
    springBootService.init(project);
    postgresqlService.init(project);

    liquibaseApplicationService.addLoggerInConfiguration(project);

    assertLoggerInConfig(project);
  }

  private void assertLoggerInConfig(Project project) {
    assertFileContent(
      project,
      getPath(MAIN_RESOURCES, LOGGING_CONFIGURATION),
      List.of("<logger name=\"liquibase\" level=\"WARN\"/>", "<logger name=\"LiquibaseSchemaResolver\" level=\"INFO\"/>")
    );

    assertFileContent(
      project,
      getPath(TEST_RESOURCES, LOGGING_TEST_CONFIGURATION),
      List.of(
        "<logger name=\"org.hibernate.validator\" level=\"WARN\"/>",
        "<logger name=\"org.hibernate\" level=\"WARN\"/>",
        "<logger name=\"org.hibernate.ejb.HibernatePersistence\" level=\"OFF\"/>"
      )
    );
  }
}
