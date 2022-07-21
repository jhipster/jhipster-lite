package tech.jhipster.lite.generator.buildtool.generic.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static tech.jhipster.lite.TestUtils.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.gradle.domain.GradleService;
import tech.jhipster.lite.generator.buildtool.maven.domain.MavenService;
import tech.jhipster.lite.generator.project.domain.Project;

@UnitTest
@ExtendWith(MockitoExtension.class)
class BuildToolDomainServiceTest {

  @Mock
  private MavenService mavenService;

  @Mock
  private GradleService gradleService;

  @InjectMocks
  private BuildToolDomainService buildToolDomainService;

  @Nested
  class MavenTest {

    @Test
    void shouldGetVersion() {
      Project project = tmpProjectWithPomXml();

      buildToolDomainService.getVersion(project, "spring-boot");

      verify(mavenService).getVersion("spring-boot");
    }

    @Test
    void shouldGetGroup() {
      Project project = tmpProjectWithPomXml();

      buildToolDomainService.getGroup(project);

      verify(mavenService).getGroupId(project.getFolder());
    }

    @Test
    void shouldGetName() {
      Project project = tmpProjectWithPomXml();

      buildToolDomainService.getName(project);

      verify(mavenService).getName(project.getFolder());
    }
  }

  @Nested
  class GradleTest {

    @Test
    void shouldGetGroup() {
      Project project = tmpProjectWithBuildGradle();

      buildToolDomainService.getGroup(project);

      verify(gradleService).getGroup(project.getFolder());
    }
  }

  @Nested
  class NoBuildToolTest {

    @Test
    void shouldNotGetVersion() {
      Project project = tmpProject();

      assertThatThrownBy(() -> buildToolDomainService.getVersion(project, "spring-boot")).isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldNotGetGroup() {
      Project project = tmpProject();

      assertThatThrownBy(() -> buildToolDomainService.getGroup(project)).isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldNotGetName() {
      Project project = tmpProject();

      assertThatThrownBy(() -> buildToolDomainService.getName(project)).isExactlyInstanceOf(GeneratorException.class);
    }
  }
}
