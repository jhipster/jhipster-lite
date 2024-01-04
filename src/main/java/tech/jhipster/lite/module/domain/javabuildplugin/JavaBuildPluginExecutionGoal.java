package tech.jhipster.lite.module.domain.javabuildplugin;

import tech.jhipster.lite.shared.error.domain.Assert;

public record JavaBuildPluginExecutionGoal(String goal) {
  public JavaBuildPluginExecutionGoal {
    Assert.notBlank("goal", goal);
  }

  public String get() {
    return goal();
  }
}
