package tech.jhipster.lite.generator.init.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static tech.jhipster.lite.TestUtils.tmpProject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class InitDomainServiceTest {

  @Mock
  private ProjectRepository projectRepository;

  @InjectMocks
  private InitDomainService initDomainService;

  @Test
  void shouldInit() {
    Project project = tmpProject();

    assertThatCode(() -> initDomainService.init(project)).doesNotThrowAnyException();
  }

  @Test
  void shouldAddPackageJson() {
    Project project = tmpProject();

    assertThatCode(() -> initDomainService.addPackageJson(project)).doesNotThrowAnyException();

    verify(projectRepository).template(any(Project.class), anyString(), anyString());
  }

  @Test
  void shouldAddReadme() {
    Project project = tmpProject();

    assertThatCode(() -> initDomainService.addReadme(project)).doesNotThrowAnyException();

    verify(projectRepository).template(any(Project.class), anyString(), anyString());
  }

  @Test
  void shouldAddGitConfiguration() {
    Project project = tmpProject();

    assertThatCode(() -> initDomainService.addGitConfiguration(project)).doesNotThrowAnyException();

    verify(projectRepository, times(2)).add(any(Project.class), anyString(), anyString(), anyString(), anyString());
  }

  @Test
  void shouldAddEditorConfiguration() {
    Project project = tmpProject();

    assertThatCode(() -> initDomainService.addEditorConfiguration(project)).doesNotThrowAnyException();

    verify(projectRepository).template(any(Project.class), anyString(), anyString());
    verify(projectRepository).add(any(Project.class), anyString(), anyString());
  }

  @Test
  void shouldAddPrettier() {
    Project project = tmpProject();

    assertThatCode(() -> initDomainService.addPrettier(project)).doesNotThrowAnyException();

    verify(projectRepository, times(2)).add(any(Project.class), anyString(), anyString());
    verify(projectRepository, times(1)).add(any(Project.class), anyString(), anyString(), anyString());
    verify(projectRepository).template(any(Project.class), anyString(), anyString());
    verify(projectRepository).setExecutable(any(Project.class), anyString(), anyString());
  }
}
