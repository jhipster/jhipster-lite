package tech.jhipster.forge.generator.springboot.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.error.domain.MissingMandatoryValueException;

@UnitTest
class DependencyTest {

  @Test
  void shouldBuildMinimal() {
    Dependency result = minimalBuilder().build();

    assertThat(result.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(result.getArtifactId()).isEqualTo("spring-boot-starter");
    assertThat(result.getVersion()).isEmpty();
    assertThat(result.getScope()).isEmpty();
  }

  @Test
  void shouldBuildFull() {
    Dependency result = fullBuilder().build();

    assertThat(result.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(result.getArtifactId()).isEqualTo("spring-boot-starter");
    assertThat(result.getVersion()).contains("2.5.3");
    assertThat(result.getScope()).contains("test");
  }

  @Test
  void shouldNotBuildWithNullGroupId() {
    Dependency.DependencyBuilder builder = fullBuilder().groupId(null);
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("groupId");
  }

  @Test
  void shouldNotBuildWithBlankGroupId() {
    Dependency.DependencyBuilder builder = fullBuilder().groupId(" ");
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("groupId");
  }

  @Test
  void shouldNotBuildWithNullArtifactId() {
    Dependency.DependencyBuilder builder = fullBuilder().artifactId(null);
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("artifactId");
  }

  @Test
  void shouldNotBuildWithBlankArtifactId() {
    Dependency.DependencyBuilder builder = fullBuilder().artifactId(" ");
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("artifactId");
  }

  @Test
  void shouldBuildWithVersion() {
    Dependency result = minimalBuilder().version("2.5.3").build();

    assertThat(result.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(result.getArtifactId()).isEqualTo("spring-boot-starter");
    assertThat(result.getVersion()).contains("2.5.3");
    assertThat(result.getScope()).isEmpty();
  }

  @Test
  void shouldBuildWithBlankVersion() {
    Dependency result = minimalBuilder().version(" ").build();

    assertThat(result.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(result.getArtifactId()).isEqualTo("spring-boot-starter");
    assertThat(result.getVersion()).isEmpty();
    assertThat(result.getScope()).isEmpty();
  }

  @Test
  void shouldBuildWithScope() {
    Dependency result = minimalBuilder().scope("test").build();

    assertThat(result.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(result.getArtifactId()).isEqualTo("spring-boot-starter");
    assertThat(result.getVersion()).isEmpty();
    assertThat(result.getScope()).contains("test");
  }

  @Test
  void shouldBuildWithBlankScope() {
    Dependency result = minimalBuilder().scope(" ").build();

    assertThat(result.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(result.getArtifactId()).isEqualTo("spring-boot-starter");
    assertThat(result.getVersion()).isEmpty();
    assertThat(result.getScope()).isEmpty();
  }

  private Dependency.DependencyBuilder minimalBuilder() {
    return Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter");
  }

  private Dependency.DependencyBuilder fullBuilder() {
    return minimalBuilder().version("2.5.3").scope("test");
  }
}
