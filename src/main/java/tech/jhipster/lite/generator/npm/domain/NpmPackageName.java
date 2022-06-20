package tech.jhipster.lite.generator.npm.domain;

import tech.jhipster.lite.error.domain.Assert;

public record NpmPackageName(String packageName) {
  public NpmPackageName {
    Assert.notBlank("packageName", packageName);
  }

  public String get() {
    return packageName();
  }
}
