package tech.jhipster.forge.common.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.jhipster.forge.error.domain.Assert;

public class FileUtils {

  private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

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
    return getPath(tmpDir(), "jhforge-test", UUID.randomUUID().toString());
  }

  public static String getPath(String... paths) {
    return String.join(File.separator, paths).replaceAll("/", File.separator).replaceAll("\\\\", File.separator);
  }

  public static Path getPathOf(String... paths) {
    return Path.of(getPath(paths));
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

  public static boolean containsInLine(String filename, String value) {
    boolean findValue = false;
    try {
      findValue = getLine(filename, value) != -1;
    } catch (IOException ex) {
      log.error("The file {} does not exist", filename);
    }
    return findValue;
  }

  public static String replace(String filename, String regexp, String replacement) throws IOException {
    String text = read(filename);
    return Pattern.compile(regexp).matcher(text).replaceAll(replacement);
  }
}
