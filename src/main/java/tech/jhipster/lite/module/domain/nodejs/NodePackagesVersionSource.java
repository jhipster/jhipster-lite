package tech.jhipster.lite.module.domain.nodejs;

import tech.jhipster.lite.shared.error.domain.Assert;

public record NodePackagesVersionSource(String name) {
  public NodePackagesVersionSource {
    Assert.notBlank("name", name);
  }

  @Override
  public String toString() {
    return name;
  }
}
