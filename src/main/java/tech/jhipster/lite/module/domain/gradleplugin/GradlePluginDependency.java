package tech.jhipster.lite.module.domain.gradleplugin;

import tech.jhipster.lite.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.shared.error.domain.Assert;

public record GradlePluginDependency(JavaDependency dependency) {
  public GradlePluginDependency {
    Assert.notNull("dependency", dependency);
  }

  public JavaDependency get() {
    return dependency();
  }
}
