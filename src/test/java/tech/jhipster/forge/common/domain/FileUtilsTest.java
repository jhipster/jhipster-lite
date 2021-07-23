package tech.jhipster.forge.common.domain;

import static org.assertj.core.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.error.domain.MissingMandatoryValueException;

@UnitTest
class FileUtilsTest {

  @Test
  void shouldNotExistsForNull() {
    assertThatThrownBy(() -> FileUtils.exists(null)).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("path");
  }

  @Test
  void shouldNotExistsForBlank() {
    assertThatThrownBy(() -> FileUtils.exists(null)).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("path");
  }

  @Test
  void shouldExists() {
    String tmp = FileUtils.tmpDir();

    assertThat(FileUtils.exists(tmp)).isTrue();
  }

  @Test
  void shouldNotCreateFolderForNull() {
    assertThatThrownBy(() -> FileUtils.createFolder(null))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("path");
  }

  @Test
  void shouldNotCreateFolderForBlank() {
    assertThatThrownBy(() -> FileUtils.createFolder(null))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("path");
  }

  @Test
  void shouldCreateFolderOnlyOnceTime() {
    String path = FileUtils.tmpDirForTest();

    assertThat(FileUtils.exists(path)).isFalse();

    assertThat(FileUtils.createFolder(path)).isTrue();
    assertThat(FileUtils.exists(path)).isTrue();

    assertThat(FileUtils.createFolder(path)).isTrue();
    assertThat(FileUtils.exists(path)).isTrue();
  }

  @Test
  void shouldNotCreateFolderWhenItsAFile() throws Exception {
    String path = FileUtils.tmpDirForTest();

    assertThat(FileUtils.exists(path)).isFalse();

    assertThat(FileUtils.createFolder(path)).isTrue();
    assertThat(FileUtils.exists(path)).isTrue();

    Files.createFile(Paths.get(path + "/chips"));
    assertThat(FileUtils.createFolder(path + "/chips")).isFalse();
  }
}
