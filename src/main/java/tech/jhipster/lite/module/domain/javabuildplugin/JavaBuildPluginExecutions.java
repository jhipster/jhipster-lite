package tech.jhipster.lite.module.domain.javabuildplugin;

import java.util.Collection;
import tech.jhipster.lite.shared.error.domain.Assert;

public record JavaBuildPluginExecutions(Collection<JavaBuildPluginExecution> executions) {
  public JavaBuildPluginExecutions {
    Assert.notEmpty("executions", executions);
  }

  public Collection<JavaBuildPluginExecution> get() {
    return executions;
  }
}
