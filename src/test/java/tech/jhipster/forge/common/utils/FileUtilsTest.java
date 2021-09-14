package tech.jhipster.forge.common.utils;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tech.jhipster.forge.TestUtils.assertFileNotExist;
import static tech.jhipster.forge.common.utils.FileUtils.getPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.error.domain.MissingMandatoryValueException;

@UnitTest
class FileUtilsTest {

  @Nested
  class Exists {

    @Test
    void shouldExists() {
      String tmp = FileUtils.tmpDir();

      assertTrue(FileUtils.exists(tmp));
    }

    @Test
    void shouldNotExistsForNull() {
      assertThatThrownBy(() -> FileUtils.exists(null))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("path");
    }

    @Test
    void shouldNotExistsForBlank() {
      assertThatThrownBy(() -> FileUtils.exists(null))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("path");
    }
  }

  @Nested
  class CreateFolder {

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
      String destinationFile = getPath(path, "chips");

      assertFalse(FileUtils.exists(path));
      assertThatCode(() -> FileUtils.createFolder(path)).doesNotThrowAnyException();
      assertTrue(FileUtils.exists(path));
      Files.createFile(FileUtils.getPathOf(destinationFile));

      assertThatThrownBy(() -> FileUtils.createFolder(destinationFile)).isInstanceOf(IOException.class);
    }
  }

  @Nested
  class GetPath {

    @Test
    void shouldGetPath() {
      String result = getPath("chips", "beer");

      assertThat(result).isEqualTo("chips" + File.separator + "beer");
    }

    @Test
    void shouldGetPathForLinux() {
      String result = getPath("/home/chips/beer");

      assertThat(result).isEqualTo(File.separator + "home" + File.separator + "chips" + File.separator + "beer");
    }

    @Test
    void shouldGetPathForWindows() {
      String result = getPath("C:\\chips\\beer");

      assertThat(result).isEqualTo("C:" + File.separator + "chips" + File.separator + "beer");
    }

    @Test
    void shouldGetPathOf() {
      Path result = FileUtils.getPathOf("chips", "beer");

      assertThat(result).isEqualTo(Path.of("chips" + File.separator + "beer"));
    }
  }

  @Nested
  class Read {

    @Test
    void shouldRead() throws Exception {
      String filename = getPath("src/test/resources/template/utils/readme-short.md");

      String result = FileUtils.read(filename);

      String lineSeparator = System.lineSeparator();
      String expectedResult = new StringBuilder()
        .append("this is a short readme")
        .append(lineSeparator)
        .append("used for unit tests")
        .append(lineSeparator)
        .append("powered by JHipster \uD83E\uDD13")
        .append(lineSeparator)
        .toString();
      assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldNotReadWhenFileNotExist() {
      String filename = getPath("src/test/resources/template/utils/unknown.md");

      assertThatThrownBy(() -> FileUtils.read(filename)).isExactlyInstanceOf(NoSuchFileException.class);
    }
  }

  @Nested
  class GetLine {

    @Test
    void shouldGetLine() throws Exception {
      String filename = getPath("src/test/resources/template/utils/readme-short.md");

      assertThat(FileUtils.getLine(filename, "used for unit tests")).isEqualTo(2);
      assertThat(FileUtils.getLine(filename, "JHipster")).isEqualTo(3);
    }

    @Test
    void shouldNotGetLineAsCaseSensitive() throws Exception {
      String filename = getPath("src/test/resources/template/utils/readme-short.md");

      assertThat(FileUtils.getLine(filename, "jhipster")).isEqualTo(-1);
    }

    @Test
    void shouldNotGetLineForAnotherText() throws Exception {
      String filename = getPath("src/test/resources/template/utils/readme-short.md");

      assertThat(FileUtils.getLine(filename, "beer")).isEqualTo(-1);
    }

    @Test
    void shouldNotGetLineWhenFileNotExist() {
      String filename = getPath("src/test/resources/template/utils/unknown.md");

      assertThatThrownBy(() -> FileUtils.getLine(filename, "beer")).isInstanceOf(IOException.class);
    }
  }

  @Nested
  class ContainsInLine {

    @Test
    void shouldContainsInLine() {
      String filename = getPath("src", "test", "resources", "template", "utils", "example-readme.md");

      assertTrue(FileUtils.containsInLine(filename, "Before you can build this project"));
    }

    @Test
    void shouldNotContainsInLine() {
      String filename = getPath("src", "test", "resources", "template", "utils", "example-readme.md");

      assertFalse(FileUtils.containsInLine(filename, "apero with beers"));
    }

    @Test
    void shouldNotContainsInLineWhenFilenameNotExist() {
      String filename = getPath("src", "test", "resources", "template", "utils", "unknown.md");

      assertFalse(FileUtils.containsInLine(filename, "apero with beers"));
    }
  }

  @Nested
  class Replace {

    @Test
    void shouldReplace() throws Exception {
      String filename = getPath("src/test/resources/template/utils/readme-short.md");

      String result = FileUtils.replace(filename, "powered by JHipster \uD83E\uDD13", "Hello JHipster Forge");

      String lineSeparator = System.lineSeparator();
      String expectedResult = new StringBuilder()
        .append("this is a short readme")
        .append(lineSeparator)
        .append("used for unit tests")
        .append(lineSeparator)
        .append("Hello JHipster Forge")
        .append(lineSeparator)
        .toString();
      assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldNotReplaceWhenFileNotExist() {
      String filename = getPath("src/test/resources/template/utils/unknown.md");

      assertThatThrownBy(() -> FileUtils.replace(filename, "powered by JHipster", "Hello JHipster Forge")).isInstanceOf(IOException.class);
    }
  }
}
