package tech.jhipster.lite.module.domain.nodejs;

import tech.jhipster.lite.shared.error.domain.Assert;

public record NodePackageName(String packageName) {
  public NodePackageName {
    Assert.notBlank("packageName", packageName);
  }

  public String get() {
    return packageName();
  }

  @Override
  public String toString() {
    return packageName;
  }
}
