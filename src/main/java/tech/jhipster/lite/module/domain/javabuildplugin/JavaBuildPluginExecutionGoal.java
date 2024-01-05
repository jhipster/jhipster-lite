package tech.jhipster.lite.module.domain.javabuildplugin;

import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public record JavaBuildPluginExecutionGoal(String goal) {
  public JavaBuildPluginExecutionGoal {
    Assert.notBlank("goal", goal);
  }

  public String get() {
    return goal();
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public String toString() {
    return get();
  }
}
