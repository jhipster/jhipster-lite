package tech.jhipster.lite.generator.server.springboot.broker.kafka.domain;

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
import tech.jhipster.lite.generator.docker.domain.DockerService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

@UnitTest
@ExtendWith(MockitoExtension.class)
class KafkaDomainServiceTest {

  @Mock
  BuildToolService buildToolService;

  @Mock
  ProjectRepository projectRepository;

  @Mock
  SpringBootCommonService springBootCommonService;

  @Mock
  DockerService dockerService;

  @InjectMocks
  KafkaDomainService kafkaDomainService;

  @Test
  void shouldInit() {
    Project project = tmpProjectWithPomXml();
    when(buildToolService.getVersion(project, "testcontainers")).thenReturn(Optional.of("0.0.0"));
    when(dockerService.getImageNameWithVersion(anyString())).thenReturn(Optional.of("dummy"));

    kafkaDomainService.init(project);

    verify(buildToolService, times(2)).addDependency(any(Project.class), any(Dependency.class));
    verify(dockerService, times(2)).getImageNameWithVersion(anyString());
    verify(projectRepository).template(any(Project.class), anyString(), anyString(), anyString(), anyString());
    verify(projectRepository, times(4)).template(any(Project.class), anyString(), anyString(), anyString());
    verify(springBootCommonService, times(9)).addProperties(any(Project.class), anyString(), any());
    verify(springBootCommonService, times(9)).addPropertiesTest(any(Project.class), anyString(), any());
    verify(springBootCommonService).updateIntegrationTestAnnotation(any(Project.class), anyString());
  }

  @Test
  void shouldAddProducerConsumer() {
    Project project = tmpProjectWithPomXml();

    when(springBootCommonService.getProperty(any(Project.class), anyString())).thenReturn(Optional.empty());
    kafkaDomainService.addDummyProducerConsumer(project);

    verify(springBootCommonService).addProperties(any(Project.class), anyString(), any());
    verify(springBootCommonService).addPropertiesTest(any(Project.class), anyString(), any());
    verify(projectRepository, times(7)).template(any(Project.class), anyString(), anyString(), anyString());
  }

  @Test
  void shouldAddAkhq() {
    Project project = tmpProjectWithPomXml();
    when(dockerService.getImageNameWithVersion(anyString())).thenReturn(Optional.of("dummy"));

    kafkaDomainService.addAkhq(project);

    verify(dockerService).getImageNameWithVersion(anyString());
    verify(projectRepository).template(any(Project.class), anyString(), anyString(), anyString(), anyString());
  }

  @Test
  void shouldNotAddProducerConsumer() {
    Project project = tmpProjectWithPomXml();

    when(springBootCommonService.getProperty(any(Project.class), anyString())).thenReturn(Optional.of("queue.jhipster.dummy"));
    kafkaDomainService.addDummyProducerConsumer(project);

    verify(springBootCommonService).getProperty(any(Project.class), anyString());
  }

  @Test
  void shouldNotAddTestcontainers() {
    Project project = tmpProjectWithPomXml();

    assertThatThrownBy(() -> kafkaDomainService.addTestcontainers(project)).isExactlyInstanceOf(GeneratorException.class);
  }
}
