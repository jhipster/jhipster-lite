package tech.jhipster.lite.generator.packagemanager.npm.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static tech.jhipster.lite.TestUtils.tmpProject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.project.domain.CommandRepository;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class NpmDomainServiceTest {

  @Mock
  CommandRepository commandRepository;

  @Mock
  ProjectRepository projectRepository;

  @InjectMocks
  NpmDomainService npmDomainService;

  @Test
  void shouldAddDependency() {
    Project project = tmpProject();
    String dependency = "prettier";
    String version = "2.5.1";

    assertThatCode(() -> npmDomainService.addDependency(project, dependency, version)).doesNotThrowAnyException();

    verify(projectRepository).replaceText(any(Project.class), anyString(), anyString(), anyString(), anyString());
  }

  @Test
  void shouldAddDevDependency() {
    Project project = tmpProject();
    String dependency = "prettier";
    String version = "2.5.1";

    assertThatCode(() -> npmDomainService.addDevDependency(project, dependency, version)).doesNotThrowAnyException();
  }

  @Test
  void shouldAddScript() {
    Project project = tmpProject();
    String name = "prepare";
    String cmd = "husky install";

    assertThatCode(() -> npmDomainService.addScript(project, name, cmd)).doesNotThrowAnyException();
  }

  @Test
  void shouldNpmInstall() {
    Project project = tmpProject();
    assertThatCode(() -> npmDomainService.install(project)).doesNotThrowAnyException();

    verify(commandRepository).npmInstall(any(Project.class));
  }

  @Test
  void shouldPrettify() {
    Project project = tmpProject();
    assertThatCode(() -> npmDomainService.prettify(project)).doesNotThrowAnyException();

    verify(commandRepository).npmPrettierFormat(any(Project.class));
  }
}
