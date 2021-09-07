package tech.jhipster.forge.common.utils;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tech.jhipster.forge.TestUtils.assertFileNotExist;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.utils.FileUtils;
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

    assertTrue(FileUtils.exists(tmp));
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

    assertFileNotExist(path);

    assertThatCode(() -> FileUtils.createFolder(path)).doesNotThrowAnyException();
    assertTrue(FileUtils.exists(path));

    assertThatCode(() -> FileUtils.createFolder(path)).doesNotThrowAnyException();
    assertTrue(FileUtils.exists(path));
  }

  @Test
  void shouldNotCreateFolderWhenItsAFile() throws Exception {
    String path = FileUtils.tmpDirForTest();
    String destinationFile = FileUtils.getPath(path, "chips");

    assertFalse(FileUtils.exists(path));
    assertThatCode(() -> FileUtils.createFolder(path)).doesNotThrowAnyException();
    assertTrue(FileUtils.exists(path));
    Files.createFile(FileUtils.getPathOf(destinationFile));

    assertThatThrownBy(() -> FileUtils.createFolder(destinationFile)).isInstanceOf(IOException.class);
  }

  @Test
  void shouldGetPath() {
    String result = FileUtils.getPath("chips", "beer");

    assertThat(result).isEqualTo("chips" + File.separator + "beer");
  }

  @Test
  void shouldGetPathOf() {
    Path result = FileUtils.getPathOf("chips", "beer");

    assertThat(result).isEqualTo(Path.of("chips" + File.separator + "beer"));
  }
}
