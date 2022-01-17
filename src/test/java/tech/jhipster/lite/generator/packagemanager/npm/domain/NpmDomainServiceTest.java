package tech.jhipster.lite.generator.packagemanager.npm.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static tech.jhipster.lite.TestUtils.tmpProject;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.MissingMandatoryValueException;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class NpmDomainServiceTest {

  @Mock
  NpmRepository npmRepository;

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

    verify(npmRepository).npmInstall(any(Project.class));
  }

  @Test
  void shouldPrettify() {
    Project project = tmpProject();
    assertThatCode(() -> npmDomainService.prettify(project)).doesNotThrowAnyException();

    verify(npmRepository).npmPrettierFormat(any(Project.class));
  }

  @Nested
  class GetVersionTest {

    @Test
    void shouldGetVersion() {
      assertThat(npmDomainService.getVersion("prettier-plugin-java")).isNotEmpty();
    }

    @Test
    void shouldNotGetVersionForNull() {
      assertThatThrownBy(() -> npmDomainService.getVersion(null))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("field");
    }

    @Test
    void shouldNotGetVersion() {
      assertThat(npmDomainService.getVersion("unknown")).isEmpty();
    }

    @Test
    void shouldNotGetVersionForDescription() {
      assertThat(npmDomainService.getVersion("description")).isEmpty();
    }

    @Test
    void shouldNotGetVersionForCloseBracket() {
      assertThat(npmDomainService.getVersion("}")).isEmpty();
    }
  }
}
