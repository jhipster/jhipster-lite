package tech.jhipster.lite.generator.project.domain;

import tech.jhipster.lite.error.domain.Assert;

public record FilePath(String folder, String file) {
  public FilePath {
    Assert.notBlank("folder", folder);
    Assert.notBlank("file", file);
  }

  public FilePath withFolder(String folder) {
    return new FilePath(folder, file);
  }
}
