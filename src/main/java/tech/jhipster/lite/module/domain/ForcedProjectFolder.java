package tech.jhipster.lite.module.domain;

import java.util.UUID;
import tech.jhipster.lite.error.domain.Assert;

public class ForcedProjectFolder implements JHipsterProjectFolderFactory {

  private final String prefix;

  public ForcedProjectFolder(String prefix) {
    Assert.notNull("prefix", prefix);

    this.prefix = prefix;
  }

  @Override
  public boolean isValid(String folderPath) {
    Assert.notNull("folderPath", folderPath);

    return folderPath.startsWith(prefix) && !folderPath.contains("..");
  }

  @Override
  public String generatePath() {
    return PathUtil.appendFileSeparatorIfNeeded(prefix) + UUID.randomUUID();
  }
}
