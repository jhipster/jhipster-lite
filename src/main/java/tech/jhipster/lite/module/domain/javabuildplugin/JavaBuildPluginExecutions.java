package tech.jhipster.lite.module.domain.javabuildplugin;

import java.util.Collection;
import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public record JavaBuildPluginExecutions(Collection<JavaBuildPluginExecution> executions) {
  public JavaBuildPluginExecutions {
    Assert.notEmpty("executions", executions);
  }

  public Collection<JavaBuildPluginExecution> get() {
    return executions;
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public String toString() {
    return executions.toString();
  }
}
