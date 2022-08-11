package tech.jhipster.lite.generator.packagemanager.npm.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static tech.jhipster.lite.TestUtils.*;

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
class NpmDomainServiceTest {

  @Mock
  private ProjectRepository projectRepository;

  @InjectMocks
  private NpmDomainService npmDomainService;

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
}
