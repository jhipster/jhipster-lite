package tech.jhipster.lite.generator.server.springboot.user.application;

import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.generator.project.domain.DatabaseType.MYSQL;
import static tech.jhipster.lite.generator.project.domain.DatabaseType.POSTGRESQL;
import static tech.jhipster.lite.generator.server.springboot.user.application.SpringBootUserAssertFiles.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;
import tech.jhipster.lite.generator.server.springboot.database.mysql.application.MySQLApplicationService;

@IntegrationTest
class SpringBootUserApplicationServiceIT {

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Autowired
  MySQLApplicationService mySQLApplicationService;

  @Autowired
  SpringBootUserApplicationService springBootUserApplicationService;

  @Test
  void shouldAddUserAndAuthorityEntitiesForPostgresql() {
    Project project = tmpProjectBuilder().build();

    springBootUserApplicationService.addUserAndAuthorityEntities(project, POSTGRESQL);

    assertFilesSqlJavaUser(project, POSTGRESQL);
    assertFilesSqlJavaUserAuthority(project, POSTGRESQL);
    assertFilesSqlJavaAuditEntity(project, POSTGRESQL);

    checkSequence(project, POSTGRESQL);
  }

  @Test
  void shouldAddUserAndAuthorityEntitiesForMysql() {
    Project project = tmpProject();
    mavenApplicationService.init(project);
    springBootApplicationService.init(project);
    mySQLApplicationService.init(project);

    springBootUserApplicationService.addUserAndAuthorityEntities(project, MYSQL);

    assertFilesSqlJavaUser(project, MYSQL);
    assertFilesSqlJavaUserAuthority(project, MYSQL);
    assertFilesSqlJavaAuditEntity(project, MYSQL);

    checkSequence(project, MYSQL);
  }
}
