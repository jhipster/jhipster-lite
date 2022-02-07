package tech.jhipster.lite.generator.server.springboot.database.sqlcommon.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static tech.jhipster.lite.TestUtils.tmpProjectWithPomXml;

import java.util.Map;
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
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

@UnitTest
@ExtendWith(MockitoExtension.class)
class SQLCommonDomainServiceTest {

  @Mock
  BuildToolService buildToolService;

  @Mock
  SpringBootCommonService springBootCommonService;

  @InjectMocks
  SQLCommonDomainService sqlCommonDomainService;

  @Test
  void shouldAddTestcontainers() {
    Project project = tmpProjectWithPomXml();
    when(buildToolService.getVersion(project, "testcontainers")).thenReturn(Optional.of("0.0.0"));

    sqlCommonDomainService.addTestcontainers(
      project,
      "postgresql",
      Map.of("spring.datasource.driver-class-name", "org.testcontainers.jdbc.ContainerDatabaseDriver")
    );

    verify(buildToolService).addProperty(project, "testcontainers.version", "0.0.0");
    verify(buildToolService).addDependency(any(Project.class), any(Dependency.class));

    verify(springBootCommonService).addPropertiesTestComment(any(Project.class), anyString());
    verify(springBootCommonService).addPropertiesTest(any(Project.class), anyString(), anyString());
    verify(springBootCommonService).addPropertiesTestNewLine(project);
  }

  @Test
  void shouldNotAddTestcontainers() {
    Project project = tmpProjectWithPomXml();
    String database = "postgresql";
    Map<String, Object> properties = Map.of();

    assertThatThrownBy(() -> sqlCommonDomainService.addTestcontainers(project, database, properties))
      .isExactlyInstanceOf(GeneratorException.class);
  }
}
