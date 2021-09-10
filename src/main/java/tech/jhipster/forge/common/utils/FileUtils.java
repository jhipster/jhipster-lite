package tech.jhipster.forge.common.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.UUID;
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
    return getPath(tmpDir(), "jhforge-test-" + UUID.randomUUID());
  }

  public static String getPath(String... paths) {
    return String.join(File.separator, paths);
  }

  public static Path getPathOf(String... paths) {
    return Path.of(getPath(paths));
  }

  public static boolean contains(String filename, String value) {
    File file = new File(filename);
    boolean findValue = false;
    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (line.contains(value)) {
          findValue = true;
          break;
        }
      }
    } catch (IOException ex) {
      log.error("The file {} does not exist", filename);
    }
    return findValue;
  }
}
