package tech.jhipster.lite.module.domain.git;

import tech.jhipster.lite.shared.error.domain.Assert;

public record GitIgnorePattern(String value) {
  public GitIgnorePattern {
    Assert.notBlank("value", value);
  }

  public String get() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }
}
