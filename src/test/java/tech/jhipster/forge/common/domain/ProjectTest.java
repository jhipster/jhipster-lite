package tech.jhipster.forge.common.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.error.domain.MissingMandatoryValueException;

@UnitTest
class ProjectTest {

  @Test
  void shouldBuild() {
    String path = FileUtils.tmpDir();
    Project project = Project.builder().path(path).build();

    assertThat(project.getPath()).isEqualTo(path);
  }

  @Test
  void shouldNotBuildWithNullPath() {
    Project.ProjectBuilder builder = Project.builder().path(null);

    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("path");
  }

  @Test
  void shouldNotBuildWithBlankPath() {
    Project.ProjectBuilder builder = Project.builder().path(" ");

    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("path");
  }
}
