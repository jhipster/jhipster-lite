package tech.jhipster.lite.module.domain.gradleconfiguration;

import tech.jhipster.lite.shared.error.domain.Assert;

public record GradleConfiguration(String configuration) {
  public GradleConfiguration {
    Assert.notBlank("instruction", configuration);
  }

  public String get() {
    return configuration();
  }
}
