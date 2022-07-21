package tech.jhipster.lite.generator.init.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

@UnitTest
class CommandLineGitRepositoryTest {

  private static final CommandLineGitRepository git = new CommandLineGitRepository();

  @Test
  void shouldInitProject() throws IOException {
    String folder = TestFileUtils.tmpDirForTest();
    Path folderPath = Paths.get(folder);
    Files.createDirectories(folderPath);

    git.init(new JHipsterProjectFolder(folder));

    assertThat(Files.isDirectory(folderPath.resolve(".git"))).isTrue();
  }
}
