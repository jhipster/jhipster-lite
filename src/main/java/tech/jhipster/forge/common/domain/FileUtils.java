package tech.jhipster.forge.common.domain;

import java.io.File;
import java.util.UUID;
import tech.jhipster.forge.error.domain.Assert;

public class FileUtils {

  public static boolean exists(String path) {
    Assert.notBlank("path", path);

    File directory = new File(path);
    return directory.exists();
  }

  public static boolean createFolder(String path) {
    if (!exists(path)) {
      File directory = new File(path);
      directory.mkdir();
      return true;
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
