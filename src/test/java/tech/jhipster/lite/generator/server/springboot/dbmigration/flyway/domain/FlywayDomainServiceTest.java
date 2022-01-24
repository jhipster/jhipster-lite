package tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static tech.jhipster.lite.TestUtils.tmpProject;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.domain.Flyway.DEFAULT_FLYWAY_ENABLED;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.domain.Flyway.DEFAULT_FLYWAY_LOCATIONS;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

@UnitTest
@ExtendWith(MockitoExtension.class)
class FlywayDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @Mock
  BuildToolService buildToolService;

  @Mock
  SpringBootCommonService springBootCommonService;

  @Spy
  Clock clock = Clock.fixed(Instant.parse("2022-01-22T14:01:54.954396664Z"), ZoneId.of("Europe/Paris"));

  @InjectMocks
  FlywayDomainService flywayDomainService;

  @Test
  void shouldInit() {
    // Given
    Project project = tmpProject();

    // When
    flywayDomainService.init(project);

    // Then
    verify(buildToolService).addProperty(project, "flyway.version", "8.4.2");

    ArgumentCaptor<Dependency> dependencyArgCaptor = ArgumentCaptor.forClass(Dependency.class);
    verify(buildToolService).addDependency(eq(project), dependencyArgCaptor.capture());
    Dependency expectedDependency = Dependency
      .builder()
      .groupId("org.flywaydb")
      .artifactId("flyway-core")
      .version("\\${flyway.version}")
      .build();
    assertThat(dependencyArgCaptor.getValue()).usingRecursiveComparison().isEqualTo(expectedDependency);

    verify(projectRepository)
      .add(
        project,
        "server/springboot/dbmigration/flyway/resources",
        "V00000000000000__init.sql",
        "src/main/resources/db/migration",
        "V20220122150154__init.sql"
      );

    verify(springBootCommonService, times(2)).addProperties(any(), anyString(), any());
    verify(springBootCommonService).addProperties(project, "spring.flyway.enabled", DEFAULT_FLYWAY_ENABLED);
    verify(springBootCommonService).addProperties(project, "spring.flyway.locations", DEFAULT_FLYWAY_LOCATIONS);
  }
}
