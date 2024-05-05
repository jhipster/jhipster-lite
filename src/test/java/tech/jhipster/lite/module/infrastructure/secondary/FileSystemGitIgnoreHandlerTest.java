package tech.jhipster.lite.module.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.lite.TestFileUtils.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.gitignore.GitIgnore;
import tech.jhipster.lite.module.domain.gitignore.GitIgnoreEntry.GitIgnorePattern;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

@UnitTest
class FileSystemGitIgnoreHandlerTest {

  private final FileSystemGitIgnoreHandler handler = new FileSystemGitIgnoreHandler();

  @Test
  void shouldNotCreateGitIgnoreFileIfPatternsAreEmpty() {
    JHipsterProjectFolder projectFolder = projectFrom("src/test/resources/projects/empty");

    handler.handle(projectFolder, new GitIgnore(List.of()));

    assertThat(projectFolder.filePath(".gitignore")).doesNotExist();
  }

  @Test
  void shouldAutomaticallyCreateMissingGitIgnoreFileIfPatternsIsNotEmpty() {
    JHipsterProjectFolder projectFolder = projectFrom("src/test/resources/projects/empty");

    handler.handle(projectFolder, new GitIgnore(List.of(new GitIgnorePattern("target/"))));

    assertThat(projectFolder.filePath(".gitignore")).exists().content().contains("target/");
  }

  @Test
  void shouldNotAddAgainAnExistingEntry() {
    JHipsterProjectFolder projectFolder = projectFrom("src/test/resources/projects/empty");
    handler.handle(projectFolder, new GitIgnore(List.of(new GitIgnorePattern("target/"))));

    handler.handle(projectFolder, new GitIgnore(List.of(new GitIgnorePattern("target/"))));

    assertThat(projectFolder.filePath(".gitignore")).content().containsOnlyOnce("target/");
  }
}
