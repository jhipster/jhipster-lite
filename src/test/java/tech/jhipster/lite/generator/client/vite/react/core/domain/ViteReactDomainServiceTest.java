package tech.jhipster.lite.generator.client.vite.react.core.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static tech.jhipster.lite.TestUtils.tmpProject;

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
class ViteReactDomainServiceTest {

  @InjectMocks
  ViteReactDomainService viteReactDomainService;

  @Mock
  NpmService npmService;

  @Mock
  ProjectRepository projectRepository;

  @Test
  void shouldInit() {
    Project project = tmpProject();
    when(npmService.getVersionInViteReact(anyString())).thenReturn(Optional.of("0.0.0"));

    viteReactDomainService.addViteReact(project);

    verify(npmService, times(2)).addDependency(any(Project.class), anyString(), anyString());
    verify(npmService, times(15)).addDevDependency(any(Project.class), anyString(), anyString());
    verify(npmService, times(6)).addScript(any(Project.class), anyString(), anyString());

    verify(projectRepository, times(3)).add(any(Project.class), anyString(), anyString());
    verify(projectRepository, times(8)).template(any(Project.class), anyString(), anyString(), anyString());
  }

  @Test
  void shouldInitStylesIndex() {
    Project project = tmpProject();
    when(npmService.getVersionInViteReact(anyString())).thenReturn(Optional.of("0.0.0"));

    viteReactDomainService.addViteReactStyledFiles(project);

    verify(projectRepository, times(2)).template(any(Project.class), anyString(), anyString(), anyString());
    verify(projectRepository, times(2)).add(any(Project.class), anyString(), anyString(), anyString());
  }

  @Test
  void shouldNotInit() {
    Project project = tmpProject();

    assertThatThrownBy(() -> viteReactDomainService.addViteReact(project)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldNotAddDependencies() {
    Project project = tmpProject();

    assertThatThrownBy(() -> viteReactDomainService.addDependencies(project)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldNotAddDevDependencies() {
    Project project = tmpProject();

    assertThatThrownBy(() -> viteReactDomainService.addDevDependencies(project)).isExactlyInstanceOf(GeneratorException.class);
  }
}
