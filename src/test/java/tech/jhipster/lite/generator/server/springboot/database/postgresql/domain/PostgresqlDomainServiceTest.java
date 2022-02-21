package tech.jhipster.lite.generator.server.springboot.database.postgresql.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static tech.jhipster.lite.TestUtils.tmpProjectWithPomXml;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.MissingMandatoryValueException;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.Level;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.database.sqlcommon.domain.SQLCommonService;

@UnitTest
@ExtendWith(MockitoExtension.class)
class PostgresqlDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @Mock
  BuildToolService buildToolService;

  @Mock
  SpringBootCommonService springBootCommonService;

  @Mock
  SQLCommonService sqlCommonService;

  @InjectMocks
  PostgresqlDomainService postgresqlDomainService;

  @Test
  void shouldNotInitWithoutProject() {
    assertThatThrownBy(() -> postgresqlDomainService.init(null))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("project");
  }

  @Test
  void shouldInit() {
    Project project = tmpProjectWithPomXml();

    postgresqlDomainService.init(project);
    verify(buildToolService).addDependency(any(Project.class), any(Dependency.class));

    verify(projectRepository, times(2)).template(any(Project.class), anyString(), anyString(), anyString());

    verify(springBootCommonService).addLogger(any(Project.class), anyString(), any(Level.class));
    verify(springBootCommonService, times(3)).addLoggerTest(any(Project.class), anyString(), any(Level.class));

    verify(sqlCommonService).addTestcontainers(any(Project.class), anyString(), anyMap());
    verify(sqlCommonService).addSpringDataJpa(project);
    verify(sqlCommonService).addHikari(project);
    verify(sqlCommonService).addHibernateCore(project);
    verify(sqlCommonService).addDockerComposeTemplate(project, "postgresql");
    verify(sqlCommonService).addJavaFiles(project, "postgresql");
    verify(sqlCommonService).addProperties(eq(project), any());
    verify(sqlCommonService).addLoggers(project);
  }
}
