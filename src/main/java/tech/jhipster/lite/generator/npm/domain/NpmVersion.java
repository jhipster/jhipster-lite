package tech.jhipster.lite.generator.npm.domain;

import tech.jhipster.lite.error.domain.Assert;

public record NpmVersion(String version) {
  public NpmVersion {
    Assert.notBlank("version", version);
  }

  public String get() {
    return version();
  }
}
