package tech.jhipster.lite.module.domain.javabuild.command;

import tech.jhipster.lite.module.domain.gradleplugin.GradlePlugin;
import tech.jhipster.lite.shared.error.domain.Assert;

public record AddGradlePlugin(GradlePlugin plugin) implements JavaBuildCommand {
  public AddGradlePlugin {
    Assert.notNull("plugin", plugin);
  }
}
