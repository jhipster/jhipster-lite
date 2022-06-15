package tech.jhipster.lite.generator.client.react.security.jwt.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
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
import tech.jhipster.lite.generator.project.domain.ProjectFilesAsserter;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class ReactJwtDomainServiceTest {

  @InjectMocks
  ReactJwtDomainService reactJwtDomainService;

  @Mock
  NpmService npmService;

  @Mock
  ProjectRepository projectRepository;

  @Test
  void shouldAddJwtReact() {
    Project project = tmpProjectWithPackageJson();
    when(npmService.getVersion(anyString(), anyString())).thenReturn(Optional.of("0.0.0"));

    assertThatCode(() -> reactJwtDomainService.addLoginReact(project)).doesNotThrowAnyException();
  }

  @Test
  void shouldAddJwtDependencies() {
    Project project = tmpProjectWithPackageJson();
    when(npmService.getVersion(anyString(), anyString())).thenReturn(Optional.of("0.0.0"));

    reactJwtDomainService.addDependencies(project);

    verify(npmService, times(4)).addDependency(any(Project.class), anyString(), anyString());
  }

  @Test
  void shouldAddJwtDevDependencies() {
    Project project = tmpProjectWithPackageJson();
    when(npmService.getVersion(anyString(), anyString())).thenReturn(Optional.of("0.0.0"));

    reactJwtDomainService.addDevDependencies(project);

    verify(npmService, times(1)).addDependency(any(Project.class), anyString(), anyString());
  }

  //  @Test
  //  void shouldAddJwtFiles() {
  //    Project project = tmpProjectWithPackageJson();
  //
  //    reactJwtDomainService.updateReactFilesForJWT(project);
  //
  //    verify(projectRepository).template(ProjectFilesAsserter.filesCountArgument(3));
  //  }

  @Test
  void shouldAddReactJwtFiles() {
    Project project = tmpProjectWithPackageJson();

    reactJwtDomainService.addReactLoginFiles(project);

    verify(projectRepository).template(ProjectFilesAsserter.filesCountArgument(9));
  }
}
