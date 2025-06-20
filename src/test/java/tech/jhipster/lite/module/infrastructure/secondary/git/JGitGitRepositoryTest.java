package tech.jhipster.lite.module.infrastructure.secondary.git;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import ch.qos.logback.classic.Level;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import tech.jhipster.lite.*;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

@UnitTest
@ExtendWith(LogsSpyExtension.class)
class JGitGitRepositoryTest {

  private static final JGitGitRepository git = new JGitGitRepository();

  @Logs
  private LogsSpy logs;

  @Nested
  class Init {

    @Test
    @EnabledOnOs(OS.LINUX)
    void shouldHandleGitInitException() throws IOException {
      String folder = createReadOnlyFolder();

      assertThatThrownBy(() -> git.init(new JHipsterProjectFolder(folder))).isExactlyInstanceOf(GitInitException.class);
    }

    private String createReadOnlyFolder() throws IOException {
      String folder = TestFileUtils.tmpDirForTest();

      Path folderPath = Path.of(folder);
      Files.createDirectories(folderPath);
      Files.setPosixFilePermissions(folderPath, Set.of(PosixFilePermission.OWNER_READ));

      return folder;
    }

    @Test
    void shouldInitProject() throws IOException {
      Path path = gitInit();

      assertThat(Files.isDirectory(path.resolve(".git"))).isTrue();
    }

    @Test
    void shouldNotInitAlreadyInitializedProject() throws IOException {
      Path path = gitInit();

      git.init(new JHipsterProjectFolder(path.toString()));

      logs.shouldHave(Level.TRACE, "already");
    }

    @Test
    void shouldNotInitInSubfolderOfAlreadyInitializedGitRepository() throws IOException {
      Path gitDirectory = gitInit();
      Path subFolder = gitDirectory.resolve("subFolder");
      Files.createDirectories(subFolder);

      git.init(new JHipsterProjectFolder(subFolder.toString()));

      logs.shouldHave(Level.TRACE, "already");
    }
  }

  @Nested
  class CommitAll {

    @Test
    void shouldHandleCommitErrors() {
      assertThatThrownBy(() ->
        git.commitAll(new JHipsterProjectFolder(TestFileUtils.tmpDirForTest()), "Add application.properties")
      ).isExactlyInstanceOf(GitCommitException.class);
    }

    @Test
    void shouldCommitAllFiles() throws IOException {
      Path path = gitInit();
      Files.copy(Path.of("src/test/resources/projects/files/dummy.txt"), path.resolve("dummy.txt"));

      git.commitAll(new JHipsterProjectFolder(path.toString()), "Add dummy.txt");

      assertThat(GitTestUtil.getCommits(path)).contains("Add dummy.txt");
      assertThat(GitTestUtil.getCurrentBranch(path)).contains("main");
    }

    @Test
    void shouldCommitAllFilesInSubfolderOfAlreadyInitializedGitRepository() throws IOException {
      Path gitDirectory = gitInit();
      Path subFolder = gitDirectory.resolve("subFolder");
      Files.createDirectories(subFolder);
      Files.copy(Path.of("src/test/resources/projects/files/dummy.txt"), subFolder.resolve("dummy.txt"));

      git.commitAll(new JHipsterProjectFolder(subFolder.toString()), "Add dummy.txt");

      assertThat(GitTestUtil.getCommits(subFolder)).contains("Add dummy.txt");
      assertThat(GitTestUtil.getCurrentBranch(subFolder)).contains("main");
    }
  }

  private Path gitInit() throws IOException {
    String folder = TestFileUtils.tmpDirForTest();
    Path folderPath = Path.of(folder);
    Files.createDirectories(folderPath);

    git.init(new JHipsterProjectFolder(folder));

    return folderPath;
  }
}
