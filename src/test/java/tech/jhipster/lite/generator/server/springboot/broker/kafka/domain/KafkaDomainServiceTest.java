package tech.jhipster.lite.generator.server.springboot.broker.kafka.domain;

import static org.mockito.Mockito.*;
import static tech.jhipster.lite.TestUtils.tmpProjectWithPomXml;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.docker.domain.DockerImage;
import tech.jhipster.lite.docker.domain.DockerImages;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

@UnitTest
@ExtendWith(MockitoExtension.class)
class KafkaDomainServiceTest {

  @Mock
  private ProjectRepository projectRepository;

  @Mock
  private SpringBootCommonService springBootCommonService;

  @Mock
  private DockerImages dockerImages;

  @InjectMocks
  private KafkaDomainService kafkaDomainService;

  @Test
  void shouldAddProducerConsumer() {
    Project project = tmpProjectWithPomXml();

    when(springBootCommonService.getProperty(eq(project), anyString())).thenReturn(Optional.empty());
    kafkaDomainService.addDummyProducerConsumer(project);

    verify(springBootCommonService).addProperties(eq(project), anyString(), any());
    verify(springBootCommonService).addPropertiesTest(eq(project), anyString(), any());
    verify(projectRepository, times(7)).template(any(ProjectFile.class));
  }

  @Test
  void shouldAddAkhq() {
    Project project = tmpProjectWithPomXml();
    when(dockerImages.get(anyString())).thenReturn(new DockerImage("dummy", "1"));

    kafkaDomainService.addAkhq(project);

    verify(dockerImages).get(anyString());
    verify(projectRepository).template(any(ProjectFile.class));
  }

  @Test
  void shouldNotAddProducerConsumer() {
    Project project = tmpProjectWithPomXml();

    when(springBootCommonService.getProperty(eq(project), anyString())).thenReturn(Optional.of("queue.jhipster.dummy"));
    kafkaDomainService.addDummyProducerConsumer(project);

    verify(springBootCommonService).getProperty(eq(project), anyString());
  }
}
