package tech.jhipster.forge.generator.refacto.application;

import static tech.jhipster.forge.TestUtils.assertFileContent;
import static tech.jhipster.forge.TestUtils.tmpProject;
import static tech.jhipster.forge.generator.buildtool.domain.maven.MavenDomainService.POM_XML;
import static tech.jhipster.forge.generator.refacto.application.LiquibaseAssertFiles.assertFilesLiquibaseChangelogMasterXml;
import static tech.jhipster.forge.generator.refacto.application.LiquibaseAssertFiles.assertFilesLiquibaseJava;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.forge.IntegrationTest;
import tech.jhipster.forge.generator.buildtool.domain.maven.MavenService;
import tech.jhipster.forge.generator.refacto.domain.core.Project;
import tech.jhipster.forge.generator.refacto.domain.server.springboot.core.SpringBootService;
import tech.jhipster.forge.generator.refacto.domain.server.springboot.database.psql.PsqlService;

@IntegrationTest
class LiquibaseApplicationServiceIT {

  @Autowired
  MavenService mavenService;

  @Autowired
  SpringBootService springBootService;

  @Autowired
  PsqlService psqlService;

  @Autowired
  LiquibaseApplicationService liquibaseApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProject();
    mavenService.init(project);
    springBootService.init(project);
    psqlService.init(project);

    liquibaseApplicationService.init(project);

    assertFileContent(
      project,
      POM_XML,
      List.of("<dependency>", "<groupId>org.liquibase</groupId>", "<artifactId>liquibase-core</artifactId>", "</dependency>")
    );
    assertFilesLiquibaseChangelogMasterXml(project);
    assertFilesLiquibaseJava(project);
  }

  @Test
  void shouldAddLiquibase() {
    Project project = tmpProject();
    mavenService.init(project);

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
  void shouldAddConfigurationJava() {
    Project project = tmpProject();

    liquibaseApplicationService.addConfigurationJava(project);

    assertFilesLiquibaseJava(project);
  }
}
