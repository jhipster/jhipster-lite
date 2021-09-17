package tech.jhipster.forge.generator.maven.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.error.domain.MissingMandatoryValueException;

@UnitTest
class ParentTest {

  @Test
  void shouldBuild() {
    Parent result = fullBuilder().build();

    assertThat(result.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(result.getArtifactId()).isEqualTo("spring-boot-starter-parent");
    assertThat(result.getVersion()).isEqualTo("2.5.3");
  }

  @Test
  void shouldNotBuildWithNullGroupId() {
    Parent.ParentBuilder builder = fullBuilder().groupId(null);
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("groupId");
  }

  @Test
  void shouldNotBuildWithBlankGroupId() {
    Parent.ParentBuilder builder = fullBuilder().groupId(" ");
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("groupId");
  }

  @Test
  void shouldNotBuildWithNullArtifactId() {
    Parent.ParentBuilder builder = fullBuilder().artifactId(null);
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("artifactId");
  }

  @Test
  void shouldNotBuildWithBlankArtifactId() {
    Parent.ParentBuilder builder = fullBuilder().artifactId(" ");
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("artifactId");
  }

  @Test
  void shouldNotBuildWithNullVersion() {
    Parent.ParentBuilder builder = fullBuilder().version(null);
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("version");
  }

  @Test
  void shouldNotBuildWithBlankVersion() {
    Parent.ParentBuilder builder = fullBuilder().version(" ");
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("version");
  }

  @Test
  void shouldGet() {
    // @formatter:off
    String expected =
        "<parent>" + System.lineSeparator() +
        "    <groupId>org.springframework.boot</groupId>" + System.lineSeparator() +
        "    <artifactId>spring-boot-starter-parent</artifactId>" + System.lineSeparator() +
        "    <version>2.5.3</version>" + System.lineSeparator() +
        "    <relativePath/>" + System.lineSeparator() +
        "  </parent>" + System.lineSeparator();
    // @formatter:on
    Parent parent = fullBuilder().build();

    assertThat(parent.get()).isEqualTo(expected);
  }

  @Test
  void shouldGetWith4Indentations() {
    // @formatter:off
    String expected =
        "<parent>" + System.lineSeparator() +
        "        <groupId>org.springframework.boot</groupId>" + System.lineSeparator() +
        "        <artifactId>spring-boot-starter-parent</artifactId>" + System.lineSeparator() +
        "        <version>2.5.3</version>" + System.lineSeparator() +
        "        <relativePath/>" + System.lineSeparator() +
        "    </parent>" + System.lineSeparator();
    // @formatter:on
    Parent parent = fullBuilder().build();

    assertThat(parent.get(4)).isEqualTo(expected);
  }

  private Parent.ParentBuilder fullBuilder() {
    return Parent.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-parent").version("2.5.3");
  }
}
