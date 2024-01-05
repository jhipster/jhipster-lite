package tech.jhipster.lite.module.domain.javabuildplugin;

import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public record JavaBuildPluginExecutionId(String executionId) {
  public JavaBuildPluginExecutionId {
    Assert.notBlank("executionId", executionId);
  }

  public String get() {
    return executionId();
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public String toString() {
    return get();
  }
}
