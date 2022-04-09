package tech.jhipster.lite.generator.buildtool.gradle.domain.gradle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.gradle.domain.GradleDomainService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static tech.jhipster.lite.TestUtils.tmpProject;

@UnitTest
@ExtendWith(MockitoExtension.class)
class GradleDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @InjectMocks
  GradleDomainService gradleDomainService;

  @Test
  void shouldInitJava() {
    Project project = tmpProject();

    gradleDomainService.initJava(project);

    verify(projectRepository, times(2)).template(any(Project.class), anyString(), anyString());
    verify(projectRepository, times(4)).add(any(Project.class), anyString(), anyString(), anyString());
    verify(projectRepository, times(2)).setExecutable(any(Project.class), anyString(), anyString());
  }

  @Test
  void shouldAddJavaBuildGradleKts() {
    Project project = tmpProject();

    gradleDomainService.addJavaBuildGradleKts(project);

    verify(projectRepository, times(2)).template(any(Project.class), anyString(), anyString());
  }

  @Test
  void shouldAddGradleWrapper() {
    Project project = tmpProject();

    gradleDomainService.addGradleWrapper(project);

    verify(projectRepository, times(4)).add(any(Project.class), anyString(), anyString(), anyString());
    verify(projectRepository, times(2)).setExecutable(any(Project.class), anyString(), anyString());
  }
}
