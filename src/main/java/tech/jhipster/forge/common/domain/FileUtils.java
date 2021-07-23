package tech.jhipster.forge.common.domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.jhipster.forge.error.domain.Assert;

public class FileUtils {

  private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

  public static boolean exists(String path) {
    Assert.notBlank("path", path);

    return Files.exists(Path.of(path));
  }

  public static boolean createFolder(String path) {
    Assert.notBlank("path", path);

    try {
      Files.createDirectories(Paths.get(path));
      return true;
    } catch (IOException ex) {
      log.error("Can't create directory {}", Paths.get(path), ex);
    }
    return false;
  }

  public static String tmpDir() {
    return System.getProperty("java.io.tmpdir");
  }

  public static String tmpDirForTest() {
    return new StringBuilder().append(tmpDir()).append(File.separator).append("jhforge-test-").append(UUID.randomUUID()).toString();
  }
}
