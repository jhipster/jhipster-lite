package tech.jhipster.lite.generator.server.springboot.database.postgresql.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static tech.jhipster.lite.TestUtils.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.MissingMandatoryValueException;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.docker.domain.DockerImage;
import tech.jhipster.lite.generator.docker.domain.DockerImages;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.Level;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.database.sqlcommon.domain.SQLCommonService;

@UnitTest
@ExtendWith(MockitoExtension.class)
class PostgresqlDomainServiceTest {

  @Mock
  private ProjectRepository projectRepository;

  @Mock
  private BuildToolService buildToolService;

  @Mock
  private SpringBootCommonService springBootCommonService;

  @Mock
  private SQLCommonService sqlCommonService;

  @Mock
  private DockerImages dockerImages;

  @InjectMocks
  private PostgresqlDomainService postgresqlDomainService;

  @Test
  void shouldNotInitWithoutProject() {
    assertThatThrownBy(() -> postgresqlDomainService.init(null))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("project");
  }

  @Test
  void shouldInit() {
    Project project = tmpProjectWithPomXml();

    when(dockerImages.get("postgres")).thenReturn(new DockerImage("postgres", "0.0.0"));

    postgresqlDomainService.init(project);

    verify(buildToolService).addDependency(any(Project.class), any(Dependency.class));

    verify(projectRepository, times(2)).template(any(ProjectFile.class));

    verify(springBootCommonService).addLogger(any(Project.class), anyString(), any(Level.class));
    verify(springBootCommonService, times(4)).addLoggerTest(any(Project.class), anyString(), any(Level.class));

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
