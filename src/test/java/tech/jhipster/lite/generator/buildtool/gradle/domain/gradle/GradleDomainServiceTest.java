package tech.jhipster.lite.generator.buildtool.gradle.domain.gradle;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.generator.project.domain.ProjectFilesAsserter.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.gradle.domain.GradleDomainService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

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

    verify(projectRepository, times(2)).template(any(ProjectFile.class));
    verify(projectRepository).add(filesCountArgument(4));
    verify(projectRepository, times(2)).setExecutable(any(Project.class), anyString(), anyString());
  }

  @Test
  void shouldAddJavaBuildGradleKts() {
    Project project = tmpProject();

    gradleDomainService.addJavaBuildGradleKts(project);

    verify(projectRepository, times(2)).template(any(ProjectFile.class));
  }

  @Test
  void shouldAddGradleWrapper() {
    Project project = tmpProject();

    gradleDomainService.addGradleWrapper(project);

    verify(projectRepository).add(filesCountArgument(4));
    verify(projectRepository, times(2)).setExecutable(any(Project.class), anyString(), anyString());
  }
}
