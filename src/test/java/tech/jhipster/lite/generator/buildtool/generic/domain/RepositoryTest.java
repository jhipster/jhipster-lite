package tech.jhipster.lite.generator.buildtool.generic.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.MissingMandatoryValueException;

@UnitTest
class RepositoryTest {

  @Test
  void shouldMinimalBuild() {
    Repository result = minimalBuilder().build();

    assertThat(result.getId()).isEqualTo("spring-milestone");
    assertThat(result.getUrl()).isEqualTo("https://repo.spring.io/milestone");
    assertThat(result.getName()).isEmpty();
  }

  @Test
  void shouldMinimalBuildWithBlankVersion() {
    Repository result = minimalBuilder().name(" ").build();

    assertThat(result.getId()).isEqualTo("spring-milestone");
    assertThat(result.getUrl()).isEqualTo("https://repo.spring.io/milestone");
    assertThat(result.getName()).isEmpty();
  }

  @Test
  void shouldMinimalBuildWithConfiguration() {
    Repository result = minimalBuilder()
      .additionalElements("""
      <releases>
        <enabled>false</enabled>
      </releases>""")
      .build();

    assertThat(result.getId()).isEqualTo("spring-milestone");
    assertThat(result.getUrl()).isEqualTo("https://repo.spring.io/milestone");
    assertThat(result.getName()).isEmpty();
    assertThat(result.getAdditionalElements()).contains("""
      <releases>
        <enabled>false</enabled>
      </releases>""");
  }

  @Test
  void shouldMinimalBuildWithBlankConfiguration() {
    Repository result = minimalBuilder().additionalElements("").build();

    assertThat(result.getId()).isEqualTo("spring-milestone");
    assertThat(result.getUrl()).isEqualTo("https://repo.spring.io/milestone");
    assertThat(result.getName()).isEmpty();
    assertThat(result.getAdditionalElements()).isEmpty();
  }

  @Test
  void shouldFullBuild() {
    Repository result = fullBuilder().build();

    assertThat(result.getId()).isEqualTo("spring-milestone");
    assertThat(result.getUrl()).isEqualTo("https://repo.spring.io/milestone");
    assertThat(result.getName()).contains("Spring Milestone");
  }

  @Test
  void shouldNotBuildWithNullId() {
    Repository.RepositoryBuilder builder = minimalBuilder().id(null);
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("id");
  }

  @Test
  void shouldNotBuildWithBlankId() {
    Repository.RepositoryBuilder builder = minimalBuilder().id(" ");
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("id");
  }

  @Test
  void shouldNotBuildWithNullArtifactId() {
    Repository.RepositoryBuilder builder = minimalBuilder().url(null);
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("url");
  }

  @Test
  void shouldNotBuildWithBlankArtifactId() {
    Repository.RepositoryBuilder builder = minimalBuilder().url(" ");
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("url");
  }

  private Repository.RepositoryBuilder minimalBuilder() {
    return Repository.builder().id("spring-milestone").url("https://repo.spring.io/milestone");
  }

  private Repository.RepositoryBuilder fullBuilder() {
    return minimalBuilder().name("Spring Milestone");
  }
}
