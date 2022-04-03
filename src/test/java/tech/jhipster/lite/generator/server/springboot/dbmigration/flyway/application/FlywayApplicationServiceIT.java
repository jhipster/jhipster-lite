package tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.application;

import static tech.jhipster.lite.TestUtils.tmpProject;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.application.FlywayAssert.assertDependencies;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.application.FlywayAssert.assertInitSqlFile;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.application.FlywayAssert.assertProperties;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.application.FlywayAssert.assertUserAuthoritySqlFile;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.application.FlywayAssert.initClock;

import java.time.Clock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;

@IntegrationTest
class FlywayApplicationServiceIT {

  @Autowired
  FlywayApplicationService flywayApplicationService;

  @SpyBean
  Clock clock;

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @BeforeEach
  void setUp() {
    initClock(clock);
  }

  @Test
  void shouldInit() {
    // Given
    Project project = tmpProject();
    project.addConfig(BASE_NAME, "foo");

    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    // When
    flywayApplicationService.init(project);

    // Then
    assertDependencies(project);
    assertInitSqlFile(project);
    assertProperties(project);
  }

  @Test
  void shouldAddUserAuthorityChangelog() {
    // Given
    Project project = tmpProject();
    project.addConfig(BASE_NAME, "foo");

    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);
    flywayApplicationService.init(project);

    // When
    flywayApplicationService.addUserAuthorityChangelog(project);

    // Then
    assertUserAuthoritySqlFile(project);
  }
}
