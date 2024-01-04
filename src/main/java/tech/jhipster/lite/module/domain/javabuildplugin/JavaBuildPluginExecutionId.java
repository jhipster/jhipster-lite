package tech.jhipster.lite.module.domain.javabuildplugin;

import tech.jhipster.lite.shared.error.domain.Assert;

public record JavaBuildPluginExecutionId(String executionId) {
  public JavaBuildPluginExecutionId {
    Assert.notBlank("executionId", executionId);
  }

  public String get() {
    return executionId();
  }
}
