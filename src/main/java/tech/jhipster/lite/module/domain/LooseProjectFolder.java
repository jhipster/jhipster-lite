package tech.jhipster.lite.module.domain;

import java.util.UUID;
import org.apache.commons.lang3.SystemUtils;

public class LooseProjectFolder implements JHipsterProjectFolderFactory {

  @Override
  public boolean isValid(String folderPath) {
    return true;
  }

  @Override
  public String generatePath() {
    return PathUtil.appendFileSeparatorIfNeeded(SystemUtils.JAVA_IO_TMPDIR) + UUID.randomUUID();
  }
}
