package tech.jhipster.lite.common.domain;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.error.domain.GeneratorException;

public class FileUtils {

  private static final Logger log = LoggerFactory.getLogger(FileUtils.class);
  private static final String FILE_SEPARATOR = "/";

  public static final String REGEXP_PREFIX_MULTILINE = "(?m)";
  public static final String REGEXP_PREFIX_DOTALL = "(?s)";
  public static final String DOT_STAR_REGEX = ".*";

  private FileUtils() {}

  public static boolean exists(String path) {
    Assert.notBlank("path", path);

    return Files.exists(Path.of(path));
  }

  public static void createFolder(String path) throws IOException {
    Assert.notBlank("path", path);

    Files.createDirectories(Paths.get(path));
  }

  public static String tmpDir() {
    return System.getProperty("java.io.tmpdir");
  }

  public static String tmpDirForTest() {
    return getPath(tmpDir(), "jhlite-test", UUID.randomUUID().toString());
  }

  public static String getPath(String... paths) {
    return String.join(FILE_SEPARATOR, paths).replace("\\", FILE_SEPARATOR);
  }

  public static Path getPathOf(String... paths) {
    return Path.of(getPath(paths));
  }

  public static InputStream getInputStream(String... paths) {
    InputStream in = FileUtils.class.getResourceAsStream(FILE_SEPARATOR + getPath(paths));
    if (in == null) {
      throw new GeneratorException("File not found in classpath");
    }
    return in;
  }

  public static String read(String filename) throws IOException {
    return Files.readString(getPathOf(filename), StandardCharsets.UTF_8);
  }

  public static int getLine(String filename, String value) throws IOException {
    File file = new File(filename);
    int lineNumber = 0;
    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        lineNumber++;
        String line = scanner.nextLine();
        if (line.contains(value)) {
          return lineNumber;
        }
      }
    }
    return -1;
  }

  public static boolean containsLines(String filename, List<String> lines) {
    Assert.notBlank("filename", filename);
    Assert.notEmpty("lines", lines);

    File file = new File(filename);
    int lineNumberToCheck = 0;
    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (!line.contains(lines.get(lineNumberToCheck))) {
          lineNumberToCheck = 0;
        } else if (lineNumberToCheck >= lines.size() - 1) {
          return true;
        } else {
          lineNumberToCheck++;
        }
      }
    } catch (IOException ex) {
      log.error("The file {} does not exist", filename);
    }
    return false;
  }

  public static boolean containsInLine(String filename, String value) {
    boolean findValue = false;
    try {
      findValue = getLine(filename, value) != -1;
    } catch (IOException ex) {
      log.error("The file {} does not exist", filename);
    }
    return findValue;
  }

  public static boolean containsRegexp(String text, String regexp) {
    return Pattern.compile(regexp).matcher(text).find();
  }

  public static long countsRegexp(String filename, String regexp) throws IOException {
    String text = read(filename);
    return Pattern.compile(regexp).matcher(text).results().count();
  }

  public static String replaceInFile(String filename, String regexp, String replacement) throws IOException {
    String text = read(filename);
    return replace(text, regexp, replacement);
  }

  public static String replace(String text, String regexp, String replacement) {
    return Pattern.compile(regexp).matcher(text).replaceAll(replacement);
  }

  public static boolean isPosix() {
    return FileSystems.getDefault().supportedFileAttributeViews().contains("posix");
  }
}
