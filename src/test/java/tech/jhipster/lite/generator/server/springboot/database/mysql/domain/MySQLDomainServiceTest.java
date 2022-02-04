package tech.jhipster.lite.generator.server.springboot.database.mysql.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static tech.jhipster.lite.TestUtils.tmpProjectWithPomXml;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.Level;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

@UnitTest
@ExtendWith(MockitoExtension.class)
class MySQLDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @Mock
  BuildToolService buildToolService;

  @Mock
  SpringBootCommonService springBootCommonService;

  @InjectMocks
  MySQLDomainService mySQLDomainService;

  @Test
  void shouldInit() {
    Project project = tmpProjectWithPomXml();
    when(buildToolService.getVersion(project, "testcontainers")).thenReturn(Optional.of("0.0.0"));

    mySQLDomainService.init(project);

    verify(buildToolService, times(5)).addDependency(any(Project.class), any(Dependency.class));
    verify(buildToolService).addProperty(any(Project.class), anyString(), anyString());

    verify(projectRepository).template(any(Project.class), anyString(), anyString(), anyString(), anyString());
    verify(projectRepository).template(any(Project.class), anyString(), anyString(), anyString());

    verify(springBootCommonService).addPropertiesComment(any(Project.class), anyString());
    verify(springBootCommonService, times(20)).addProperties(any(Project.class), anyString(), any());
    verify(springBootCommonService).addPropertiesNewLine(any(Project.class));
    verify(springBootCommonService, times(3)).addLogger(any(Project.class), anyString(), any(Level.class));
    verify(springBootCommonService, times(5)).addLoggerTest(any(Project.class), anyString(), any(Level.class));
    verify(springBootCommonService).addPropertiesTestComment(any(Project.class), anyString());
    verify(springBootCommonService, times(5)).addPropertiesTest(any(Project.class), anyString(), any());
    verify(springBootCommonService).addPropertiesTestNewLine(any(Project.class));
  }

  @Test
  void shouldNotAddTestcontainers() {
    Project project = tmpProjectWithPomXml();

    assertThatThrownBy(() -> mySQLDomainService.addTestcontainers(project)).isExactlyInstanceOf(GeneratorException.class);
  }
}
