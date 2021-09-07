package tech.jhipster.forge.common.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.utils.FileUtils;
import tech.jhipster.forge.error.domain.MissingMandatoryValueException;

@UnitTest
class ProjectTest {

  @Test
  void shouldBuild() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();

    assertThat(project.getPath()).isEqualTo(path);
    assertThat(project.getConfig()).isEqualTo(Map.of());
  }

  @Test
  void shouldBuildWithConfig() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).config(Map.of("projectName", "JHipster Forge")).build();

    assertThat(project.getPath()).isEqualTo(path);
    assertThat(project.getConfig()).isEqualTo(Map.of("projectName", "JHipster Forge"));
  }

  @Test
  void shouldBuildWithNullConfig() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).config(null).build();

    assertThat(project.getPath()).isEqualTo(path);
    assertThat(project.getConfig()).isEqualTo(Map.of());
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

  @Test
  void shouldGetConfigWithKey() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).config(Map.of("projectName", "JHipster Forge")).build();

    assertThat(project.getConfig("projectName")).contains("JHipster Forge");

    assertThat(project.getConfig("projectname")).isEmpty();
    assertThat(project.getConfig("test")).isEmpty();
  }

  @Test
  void shouldGetDefaultConfigWithKey() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();

    assertThat(project.getConfig("baseName")).contains("jhipster");
  }

  @Test
  void shouldAddConfigInEmptyConfig() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();

    assertThat(project.getConfig("apero")).isEmpty();

    project.addConfig("apero", "beer");
    assertThat(project.getConfig("apero")).contains("beer");

    project.addConfig("apero", "chips");
    assertThat(project.getConfig("apero")).contains("beer");
  }

  @Test
  void shouldAddConfigInExistingConfig() {
    // Given
    String path = FileUtils.tmpDirForTest();
    Map<String, String> config = new HashMap<>();
    config.putIfAbsent("projectName", "JHipster Forge");
    Project project = Project.builder().path(path).config(config).build();

    assertThat(project.getConfig("projectName")).contains("JHipster Forge");
    assertThat(project.getConfig("apero")).isEmpty();

    // When + Then
    project.addConfig("apero", "beer");
    assertThat(project.getConfig("apero")).contains("beer");

    // When + Then
    project.addConfig("projectName", "chips");
    assertThat(project.getConfig("projectName")).contains("JHipster Forge");
  }

  @Test
  void shouldAddDefaultConfig() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();

    project.addDefaultConfig("baseName");

    assertThat(project.getConfig("baseName")).contains("jhipster");
  }

  @Test
  void shouldNotAddDefaultConfig() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();

    project.addDefaultConfig("apero");

    assertThat(project.getConfig("apero")).isEmpty();
  }
}
