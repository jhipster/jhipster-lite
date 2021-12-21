package tech.jhipster.lite.generator.buildtool.generic.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.MissingMandatoryValueException;

@UnitTest
class PluginTest {

  @Test
  void shouldMinimalBuild() {
    Plugin result = minimalBuilder().build();

    assertThat(result.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(result.getArtifactId()).isEqualTo("spring-boot-maven-plugin");
    assertThat(result.getVersion()).isEmpty();
  }

  @Test
  void shouldMinimalBuildWithBlankVersion() {
    Plugin result = minimalBuilder().version(" ").build();

    assertThat(result.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(result.getArtifactId()).isEqualTo("spring-boot-maven-plugin");
    assertThat(result.getVersion()).isEmpty();
  }

  @Test
  void shouldMinimalBuildWithConfiguration() {
    Plugin result = minimalBuilder()
      .additionalElements("""
      <executions>
        <execution>
          <goal>clean</goal>
        </execution>
      </executions>""")
      .build();

    assertThat(result.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(result.getArtifactId()).isEqualTo("spring-boot-maven-plugin");
    assertThat(result.getVersion()).isEmpty();
    assertThat(result.getAdditionalElements())
      .contains("""
      <executions>
        <execution>
          <goal>clean</goal>
        </execution>
      </executions>""");
  }

  @Test
  void shouldMinimalBuildWithBlankConfiguration() {
    Plugin result = minimalBuilder().additionalElements("").build();

    assertThat(result.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(result.getArtifactId()).isEqualTo("spring-boot-maven-plugin");
    assertThat(result.getVersion()).isEmpty();
    assertThat(result.getAdditionalElements()).isEmpty();
  }

  @Test
  void shouldFullBuild() {
    Plugin result = fullBuilder().build();

    assertThat(result.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(result.getArtifactId()).isEqualTo("spring-boot-maven-plugin");
    assertThat(result.getVersion()).contains("2.6.0");
  }

  @Test
  void shouldNotBuildWithNullGroupId() {
    Plugin.PluginBuilder builder = minimalBuilder().groupId(null);
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("groupId");
  }

  @Test
  void shouldNotBuildWithBlankGroupId() {
    Plugin.PluginBuilder builder = minimalBuilder().groupId(" ");
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("groupId");
  }

  @Test
  void shouldNotBuildWithNullArtifactId() {
    Plugin.PluginBuilder builder = minimalBuilder().artifactId(null);
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("artifactId");
  }

  @Test
  void shouldNotBuildWithBlankArtifactId() {
    Plugin.PluginBuilder builder = minimalBuilder().artifactId(" ");
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("artifactId");
  }

  private Plugin.PluginBuilder minimalBuilder() {
    return Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin");
  }

  private Plugin.PluginBuilder fullBuilder() {
    return minimalBuilder().version("2.6.0");
  }
}
