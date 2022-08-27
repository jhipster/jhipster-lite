package tech.jhipster.lite.common.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static tech.jhipster.lite.common.domain.FileUtils.*;
import static tech.jhipster.lite.common.domain.WordUtils.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import tech.jhipster.lite.UnitTest;

@UnitTest
class FileUtilsTest {

  @Nested
  class GetPathTest {

    @Test
    void shouldGetPath() {
      String result = getPath("chips", "beer");

      assertThat(result).isEqualTo("chips/beer");
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void shouldGetPathForLinux() {
      String result = getPath("/home/chips/beer");

      assertThat(result).isEqualTo(File.separator + "home" + File.separator + "chips" + File.separator + "beer");
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void shouldGetPathForWindows() {
      String result = getPath("C:\\chips\\beer");

      assertThat(result).isEqualTo("C:/chips/beer");
    }
  }

  @Nested
  class DetectEndOfLineTest {

    @Test
    void shouldDetectLf() throws Exception {
      Path tempFile = Files.createTempFile("detect-lf", null);
      Files.writeString(tempFile, "my little file \nwith lf");

      assertThat(detectEndOfLine(tempFile.toString())).hasValue(LF);
    }

    @Test
    void shouldDetectCrLf() throws Exception {
      Path tempFile = Files.createTempFile("detect-crlf", null);
      Files.writeString(tempFile, "my little file \r\nwith crlf");

      assertThat(detectEndOfLine(tempFile.toString())).hasValue(WordUtils.CRLF);
    }

    @Test
    void shouldNotDetectEndOfLine() throws Exception {
      Path tempFile = Files.createTempFile("detect-none", null);
      Files.writeString(tempFile, "my little file without new line");

      assertThat(detectEndOfLine(tempFile.toString())).isEmpty();
    }
  }

  @Nested
  class FileSystemTest {

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void shouldReturnPosixFalseForWindows() {
      assertFalse(isPosix());
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void shouldReturnPosixTrueForNonWindows() {
      assertTrue(isPosix());
    }
  }
}
