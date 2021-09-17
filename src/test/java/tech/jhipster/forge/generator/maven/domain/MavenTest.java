package tech.jhipster.forge.generator.maven.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tech.jhipster.forge.TestUtils.tmpProject;
import static tech.jhipster.forge.common.utils.FileUtils.getPath;

import java.nio.file.Files;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.utils.FileUtils;
import tech.jhipster.forge.error.domain.MissingMandatoryValueException;
import tech.jhipster.forge.error.domain.UnauthorizedValueException;

@UnitTest
public class MavenTest {

  @Nested
  class IsMavenProject {

    @Test
    void shouldNotBeMavenProject() {
      Project project = tmpProject();

      assertFalse(Maven.isMavenProject(project));
    }

    @Test
    void shouldBeMavenProject() throws Exception {
      Project project = tmpProject();
      String destinationFile = getPath(project.getPath(), "pom.xml");
      FileUtils.createFolder(project.getPath());
      Files.createFile(FileUtils.getPathOf(destinationFile));

      assertTrue(Maven.isMavenProject(project));
    }
  }

  @Nested
  class GetParent {

    @Test
    void shouldGetParent() {
      // @formatter:off
      String expected =
        "  <parent>" + System.lineSeparator() +
        "    <groupId>org.springframework.boot</groupId>" + System.lineSeparator() +
        "    <artifactId>spring-boot-starter-parent</artifactId>" + System.lineSeparator() +
        "    <version>2.5.3</version>" + System.lineSeparator() +
        "    <relativePath/>" + System.lineSeparator() +
        "  </parent>" + System.lineSeparator();
      // @formatter:on

      String result = Maven.getParent("org.springframework.boot", "spring-boot-starter-parent", "2.5.3");

      assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldGetParentWith4Indentations() {
      // @formatter:off
      String expected =
        "    <parent>" + System.lineSeparator() +
        "        <groupId>org.springframework.boot</groupId>" + System.lineSeparator() +
        "        <artifactId>spring-boot-starter-parent</artifactId>" + System.lineSeparator() +
        "        <version>2.5.3</version>" + System.lineSeparator() +
        "        <relativePath/>" + System.lineSeparator() +
        "    </parent>" + System.lineSeparator();
      // @formatter:on

      String result = Maven.getParent("org.springframework.boot", "spring-boot-starter-parent", "2.5.3", 4);

      assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldNotGetParentWithNullGroupId() {
      assertThatThrownBy(() -> Maven.getParent(null, "spring-boot-starter-parent", "2.5.3"))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("groupId");
    }

    @Test
    void shouldNotGetParentWithBlankGroupId() {
      assertThatThrownBy(() -> Maven.getParent(" ", "spring-boot-starter-parent", "2.5.3"))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("groupId");
    }

    @Test
    void shouldNotGetParentWithNullArtifactId() {
      assertThatThrownBy(() -> Maven.getParent("org.springframework.boot", null, "2.5.3"))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("artifactId");
    }

    @Test
    void shouldNotGetParentWithBlankArtifactId() {
      assertThatThrownBy(() -> Maven.getParent("org.springframework.boot", " ", "2.5.3"))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("artifactId");
    }

    @Test
    void shouldNotGetParentWithNullVersion() {
      assertThatThrownBy(() -> Maven.getParent("org.springframework.boot", "spring-boot-starter-parent", null))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("version");
    }

    @Test
    void shouldNotGetParentWithBlankVersion() {
      assertThatThrownBy(() -> Maven.getParent("org.springframework.boot", "spring-boot-starter-parent", " "))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("version");
    }
  }

  @Nested
  class GetDependencies {

    @Test
    void shouldGetDependencies() {
      // @formatter:off
      String expected =
        "    <dependency>" + System.lineSeparator() +
        "      <groupId>org.springframework.boot</groupId>" + System.lineSeparator() +
        "      <artifactId>spring-boot-starter</artifactId>" + System.lineSeparator() +
        "    </dependency>" + System.lineSeparator();
      // @formatter:on

      String result = Maven.getDependencies("org.springframework.boot", "spring-boot-starter");

      assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldGetDependenciesWithScopeTest() {
      // @formatter:off
      String expected =
        "    <dependency>" + System.lineSeparator() +
        "      <groupId>org.springframework.boot</groupId>" + System.lineSeparator() +
        "      <artifactId>spring-boot-starter-test</artifactId>" + System.lineSeparator() +
        "      <scope>test</scope>" + System.lineSeparator() +
        "    </dependency>" + System.lineSeparator();
      // @formatter:on

      String result = Maven.getDependencies("org.springframework.boot", "spring-boot-starter-test", true);

      assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldGetDependenciesWith4Indentations() {
      // @formatter:off
      String expected =
        "        <dependency>" + System.lineSeparator() +
        "            <groupId>org.springframework.boot</groupId>" + System.lineSeparator() +
        "            <artifactId>spring-boot-starter</artifactId>" + System.lineSeparator() +
        "        </dependency>" + System.lineSeparator();
      // @formatter:on

      String result = Maven.getDependencies("org.springframework.boot", "spring-boot-starter", 4);

      assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldNotGetDependenciesWithNullGroupId() {
      assertThatThrownBy(() -> Maven.getDependencies(null, "spring-boot-starter"))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("groupId");
    }

    @Test
    void shouldNotGetDependenciesWithBlankGroupId() {
      assertThatThrownBy(() -> Maven.getDependencies(" ", "spring-boot-starter"))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("groupId");
    }

    @Test
    void shouldNotGetDependenciesWithNullArtifactId() {
      assertThatThrownBy(() -> Maven.getDependencies("org.springframework.boot", null))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("artifactId");
    }

    @Test
    void shouldNotGetDependenciesWithBlankArtifactId() {
      assertThatThrownBy(() -> Maven.getDependencies("org.springframework.boot", " "))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("artifactId");
    }

    @Test
    void shouldNotGetDependenciesWithNegativeIndentation() {
      assertThatThrownBy(() -> Maven.getDependencies("org.springframework.boot", "spring-boot-starter-parent", -1))
        .isExactlyInstanceOf(UnauthorizedValueException.class)
        .hasMessageContaining("spaceNumber");
    }

    @Test
    void shouldNotGetParentWithZeroIndentation() {
      assertThatThrownBy(() -> Maven.getDependencies("org.springframework.boot", "spring-boot-starter-parent", 0))
        .isExactlyInstanceOf(UnauthorizedValueException.class)
        .hasMessageContaining("spaceNumber");
    }
  }
}
