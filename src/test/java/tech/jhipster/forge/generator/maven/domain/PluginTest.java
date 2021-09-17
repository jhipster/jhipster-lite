package tech.jhipster.forge.generator.maven.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.error.domain.MissingMandatoryValueException;

@UnitTest
class PluginTest {

  @Test
  void shouldBuild() {
    Plugin result = fullBuilder().build();

    assertThat(result.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(result.getArtifactId()).isEqualTo("spring-boot-maven-plugin");
  }

  @Test
  void shouldNotBuildWithNullGroupId() {
    Plugin.PluginBuilder builder = fullBuilder().groupId(null);
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("groupId");
  }

  @Test
  void shouldNotBuildWithBlankGroupId() {
    Plugin.PluginBuilder builder = fullBuilder().groupId(" ");
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("groupId");
  }

  @Test
  void shouldNotBuildWithNullArtifactId() {
    Plugin.PluginBuilder builder = fullBuilder().artifactId(null);
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("artifactId");
  }

  @Test
  void shouldNotBuildWithBlankArtifactId() {
    Plugin.PluginBuilder builder = fullBuilder().artifactId(" ");
    assertThatThrownBy(builder::build).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("artifactId");
  }

  @Test
  void shouldGet() {
    // @formatter:off
    String expected =
      "<plugin>" + System.lineSeparator() +
      "        <groupId>org.springframework.boot</groupId>" + System.lineSeparator() +
      "        <artifactId>spring-boot-maven-plugin</artifactId>" + System.lineSeparator() +
      "      </plugin>";
    // @formatter:on
    Plugin plugin = fullBuilder().build();

    assertThat(plugin.get()).isEqualTo(expected);
  }

  @Test
  void shouldGetWith4Indentations() {
    // @formatter:off
    String expected =
      "<plugin>" + System.lineSeparator() +
      "                <groupId>org.springframework.boot</groupId>" + System.lineSeparator() +
      "                <artifactId>spring-boot-maven-plugin</artifactId>" + System.lineSeparator() +
      "            </plugin>";
    // @formatter:on
    Plugin plugin = fullBuilder().build();

    assertThat(plugin.get(4)).isEqualTo(expected);
  }

  private Plugin.PluginBuilder fullBuilder() {
    return Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin");
  }
}
