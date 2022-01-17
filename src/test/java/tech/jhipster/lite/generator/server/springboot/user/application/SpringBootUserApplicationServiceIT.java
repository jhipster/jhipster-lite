package tech.jhipster.lite.generator.server.springboot.user.application;

import static tech.jhipster.lite.TestUtils.tmpProjectBuilder;
import static tech.jhipster.lite.generator.project.domain.DatabaseType.POSTGRESQL;
import static tech.jhipster.lite.generator.server.springboot.user.application.SpringBootUserAssertFiles.assertFilesSqlJavaAuditEntity;
import static tech.jhipster.lite.generator.server.springboot.user.application.SpringBootUserAssertFiles.assertFilesSqlJavaUser;
import static tech.jhipster.lite.generator.server.springboot.user.application.SpringBootUserAssertFiles.assertFilesSqlJavaUserAuthority;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class SpringBootUserApplicationServiceIT {

  @Autowired
  SpringBootUserApplicationService springBootUserApplicationService;

  @Test
  void shouldAddSqlJavaUser() {
    Project project = tmpProjectBuilder().build();

    springBootUserApplicationService.addSqlJavaUser(project, POSTGRESQL);

    assertFilesSqlJavaUser(project, POSTGRESQL);
  }

  @Test
  void shouldAddSqlJavaUserAuthority() {
    Project project = tmpProjectBuilder().build();

    springBootUserApplicationService.addSqlJavaAuthority(project, POSTGRESQL);

    assertFilesSqlJavaUserAuthority(project, POSTGRESQL);
  }

  @Test
  void shouldAddSqlJavaAuditEntity() {
    Project project = tmpProjectBuilder().build();

    springBootUserApplicationService.addSqlJavaAuditEntity(project, POSTGRESQL);

    assertFilesSqlJavaAuditEntity(project, POSTGRESQL);
  }
}
