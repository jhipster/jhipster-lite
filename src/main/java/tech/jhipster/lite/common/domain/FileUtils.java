package tech.jhipster.lite.common.domain;

import static tech.jhipster.lite.common.domain.WordUtils.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.Optional;

@Generated(reason = "Candidate for deletion")
public class FileUtils {

  private static final String FILE_SEPARATOR = "/";

  private FileUtils() {}

  public static String getPath(String... paths) {
    return String.join(FILE_SEPARATOR, paths).replace("\\", FILE_SEPARATOR);
  }

  public static boolean isPosix() {
    return FileSystems.getDefault().supportedFileAttributeViews().contains("posix");
  }

  public static Optional<String> detectEndOfLine(String filename) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
      char previousChar = 0;
      char currentChar;
      int read;
      while ((read = reader.read()) != -1) {
        currentChar = (char) read;
        if (currentChar == '\n') {
          if (previousChar == '\r') {
            return Optional.of(CRLF);
          }
          return Optional.of(LF);
        }
        previousChar = currentChar;
      }
    }
    return Optional.empty();
  }

  public static String normalizeEndOfLine(String text) {
    return transformEndOfLine(text, LF);
  }

  private static String transformEndOfLine(String text, String toEndOfLine) {
    if (LF.equals(toEndOfLine)) {
      return text.replace(CRLF, LF);
    }
    return text.replaceAll("([^\\r])\\n", "$1" + toEndOfLine);
  }
}
