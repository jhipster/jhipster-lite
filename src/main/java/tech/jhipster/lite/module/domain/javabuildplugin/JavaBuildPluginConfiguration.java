package tech.jhipster.lite.module.domain.javabuildplugin;

import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public record JavaBuildPluginConfiguration(String configuration) {
  public JavaBuildPluginConfiguration {
    Assert.notBlank("configuration", configuration);
  }

  public String get() {
    return configuration();
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public String toString() {
    return get();
  }
}
