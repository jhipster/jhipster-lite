package tech.jhipster.lite.generator.client.vue.core.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static tech.jhipster.lite.TestUtils.tmpProject;
import static tech.jhipster.lite.TestUtils.tmpProjectWithPackageJson;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class VueDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @Mock
  NpmService npmService;

  @InjectMocks
  private VueDomainService vueDomainService;

  @Test
  void shouldAddVue() {
    Project project = tmpProjectWithPackageJson();
    when(npmService.getVersion(anyString(), anyString())).thenReturn(Optional.of("0.0.0"));

    assertThatCode(() -> vueDomainService.addVue(project)).doesNotThrowAnyException();
  }

  @Test
  void shouldNotAddVue() {
    Project project = tmpProjectWithPackageJson();

    assertThatThrownBy(() -> vueDomainService.addVue(project)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldAddStyledVue() {
    Project project = tmpProjectWithPackageJson();
    when(npmService.getVersion(anyString(), anyString())).thenReturn(Optional.of("0.0.0"));

    assertThatCode(() -> vueDomainService.addStyledVue(project)).doesNotThrowAnyException();
  }

  @Test
  void shouldNotAddStyledIndex() {
    Project project = tmpProjectWithPackageJson();

    assertThatThrownBy(() -> vueDomainService.addStyledVue(project)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldAddDependencies() {
    Project project = tmpProjectWithPackageJson();
    when(npmService.getVersion(anyString(), anyString())).thenReturn(Optional.of("0.0.0"));

    vueDomainService.addDependencies(project);

    verify(npmService, times(1)).addDependency(any(Project.class), anyString(), anyString());
  }

  @Test
  void shouldNotAddDependencies() {
    Project project = tmpProjectWithPackageJson();

    assertThatThrownBy(() -> vueDomainService.addDependencies(project)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldAddDevDependencies() {
    Project project = tmpProjectWithPackageJson();
    when(npmService.getVersion(anyString(), anyString())).thenReturn(Optional.of("0.0.0"));

    vueDomainService.addDevDependencies(project);

    verify(npmService, times(17)).addDevDependency(any(Project.class), anyString(), anyString());
  }

  @Test
  void shouldNotAddDevDependencies() {
    Project project = tmpProjectWithPackageJson();

    assertThatThrownBy(() -> vueDomainService.addDevDependencies(project)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldAddScripts() {
    Project project = tmpProjectWithPackageJson();

    vueDomainService.addScripts(project);

    verify(npmService, times(7)).addScript(any(Project.class), anyString(), anyString());
  }

  @Test
  void shouldAddViteConfigFiles() {
    Project project = tmpProjectWithPackageJson();

    vueDomainService.addViteConfigFiles(project);

    verify(projectRepository, times(4)).add(any(Project.class), anyString(), anyString());
  }

  @Test
  void shouldAddRootFiles() {
    Project project = tmpProject();

    vueDomainService.addRootFiles(project);

    verify(projectRepository, times(3)).template(any(Project.class), anyString(), anyString(), anyString());
  }

  @Test
  void shouldAddAppFiles() {
    Project project = tmpProject();

    vueDomainService.addAppFiles(project);

    verify(projectRepository, times(3)).template(any(Project.class), anyString(), anyString(), anyString());
  }

  @Test
  void shouldAddAppFilesWithoutCss() {
    Project project = tmpProject();

    vueDomainService.addAppFilesWithoutCss(project);

    verify(projectRepository, times(2)).template(any(Project.class), anyString(), anyString(), anyString());
  }

  @Test
  void shouldAddAppFilesWithCss() {
    Project project = tmpProject();

    vueDomainService.addAppFilesWithCss(project);

    verify(projectRepository, times(2)).template(any(Project.class), anyString(), anyString(), anyString(), anyString());
    verify(projectRepository, times(2)).add(any(Project.class), anyString(), anyString(), anyString());
  }
}
