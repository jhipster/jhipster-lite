package tech.jhipster.lite.project.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.project.domain.Project;
import tech.jhipster.lite.project.domain.ProjectName;
import tech.jhipster.lite.project.domain.ProjectPath;

@UnitTest
class FileSystemProjectsRepositoryTest {

  private static final FileSystemProjectsRepository projects = new FileSystemProjectsRepository();

  @Test
  void shouldGetEmptyProjectFromUnknownFolder() {
    assertThat(projects.get(new ProjectPath(TestFileUtils.tmpDirForTest()))).isEmpty();
  }

  @Test
  void shouldNotGetProjectFromEmptyFolder() throws IOException {
    String folder = TestFileUtils.tmpDirForTest();
    Files.createDirectories(Paths.get(folder));

    assertThatThrownBy(() -> projects.get(new ProjectPath(folder))).isExactlyInstanceOf(ProjectZippingException.class);
  }

  @Test
  void shouldGetEmptyProjectFromFile() {
    ProjectPath path = folder().addFile("src/test/resources/projects/maven/pom.xml").build();

    ProjectPath filePath = new ProjectPath(path.get() + "/pom.xml");

    assertThat(projects.get(filePath)).isEmpty();
  }

  @Test
  void shouldGetZippedProjectFromFolderWithoutPackageJson() {
    ProjectPath path = folder().addFile("src/test/resources/projects/maven/pom.xml").build();

    Project project = projects.get(path).get();

    assertThat(project.name()).isEqualTo(ProjectName.DEFAULT);
    assertThat(zipFiles(project.content())).containsExactly("pom.xml");
  }

  @Test
  void shouldGetZippedProjectFromFolderEmptyPackageJson() {
    ProjectPath path = folder().addFile("src/test/resources/projects/empty-package-json/package.json").build();

    Project project = projects.get(path).get();

    assertThat(project.name()).isEqualTo(ProjectName.DEFAULT);
    assertThat(zipFiles(project.content())).containsExactly("package.json");
  }

  @Test
  void shouldGetZippedProjectFromFolderPackageJsonWithProjectName() {
    ProjectPath path = folder().addFile("src/test/resources/projects/package-json/package.json").build();

    Project project = projects.get(path).get();

    assertThat(project.name()).isEqualTo(new ProjectName("jhipster-project"));
    assertThat(zipFiles(project.content())).containsExactly("package.json");
  }

  private Collection<String> zipFiles(byte[] content) {
    try (ZipInputStream zip = new ZipInputStream(new ByteArrayInputStream(content))) {
      ZipEntry entry = zip.getNextEntry();

      List<String> result = new ArrayList<>();
      while (entry != null) {
        result.add(entry.getName());

        entry = zip.getNextEntry();
      }

      return result;
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  private static FolderBuilder folder() {
    return new FolderBuilder();
  }

  private static class FolderBuilder {

    private final Path folder;

    public FolderBuilder() {
      folder = Paths.get(TestFileUtils.tmpDirForTest());

      createFolder();
    }

    private void createFolder() throws AssertionError {
      try {
        Files.createDirectories(folder);
      } catch (IOException e) {
        throw new AssertionError(e);
      }
    }

    public FolderBuilder addFile(String source) {
      Path sourcePath = Paths.get(source);

      try {
        Files.copy(sourcePath, folder.resolve(sourcePath.getFileName().toString()));
      } catch (IOException e) {
        throw new AssertionError(e);
      }

      return this;
    }

    public ProjectPath build() {
      return new ProjectPath(folder.toString());
    }
  }
}
