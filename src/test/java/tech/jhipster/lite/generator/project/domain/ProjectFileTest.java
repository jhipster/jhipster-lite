package tech.jhipster.lite.generator.project.domain;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.lite.TestUtils.*;

import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class ProjectFileTest {

  private final Project project = tmpProject();

  @Test
  void shouldGetFileWithDefaultDestination() {
    ProjectFile file = ProjectFile.forProject(project).withSource("folder", "file.java").withSameDestination();

    assertThat(file.project()).isEqualTo(project);
    assertThat(file.source()).isEqualTo(new FilePath("folder", "file.java"));
    assertThat(file.destination()).isEqualTo(new FilePath(".", "file.java"));
  }

  @Test
  void shouldGetFileWithCustomDestinationFolder() {
    ProjectFile file = ProjectFile.forProject(project).withSource("folder", "file.java").withDestinationFolder("anotherFolder");

    assertThat(file.project()).isEqualTo(project);
    assertThat(file.source()).isEqualTo(new FilePath("folder", "file.java"));
    assertThat(file.destination()).isEqualTo(new FilePath("anotherFolder", "file.java"));
  }

  @Test
  void shouldGetFileWithCustomDestinationFile() {
    ProjectFile file = ProjectFile.forProject(project).withSource("folder", "file.java").withDestinationFile("anotherFile.java");

    assertThat(file.project()).isEqualTo(project);
    assertThat(file.source()).isEqualTo(new FilePath("folder", "file.java"));
    assertThat(file.destination()).isEqualTo(new FilePath(".", "anotherFile.java"));
  }

  @Test
  void shouldGetFileWithCustomDestination() {
    ProjectFile file = ProjectFile
      .forProject(project)
      .withSource("folder", "file.java")
      .withDestination("anotherFolder", "anotherFile.java");

    assertThat(file.project()).isEqualTo(project);
    assertThat(file.source()).isEqualTo(new FilePath("folder", "file.java"));
    assertThat(file.destination()).isEqualTo(new FilePath("anotherFolder", "anotherFile.java"));
  }

  @Test
  void shouldGetAllFiles() {
    Collection<ProjectFile> files = ProjectFile.forProject(project).all("source", List.of("first", "second"));

    assertThat(files).hasSize(2);
    assertThat(files).extracting(file -> file.source()).containsExactly(new FilePath("source", "first"), new FilePath("source", "second"));
  }
}
