package tech.jhipster.lite.module.domain;

import java.io.File;
import tech.jhipster.lite.error.domain.Assert;

class PathUtil {

  private PathUtil() {}

  static String appendFileSeparatorIfNeeded(String path) {
    Assert.notNull("path", path);

    if (path.endsWith(File.separator)) {
      return path;
    }

    return path + File.separator;
  }
}
