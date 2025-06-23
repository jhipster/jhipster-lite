package tech.jhipster.lite.module.domain.nodejs;

import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public record NodePackageVersion(String version) {
  public NodePackageVersion {
    Assert.notBlank("version", version);
  }

  public String get() {
    return version();
  }

  public String majorVersion() {
    return version().split("\\.", -1)[0];
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public String toString() {
    return version;
  }
}
