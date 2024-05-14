package tech.jhipster.lite.module.domain.javabuild.command;

import tech.jhipster.lite.shared.error.domain.Assert;

public record AddGradleConfiguration(String configuration) implements JavaBuildCommand {
  public AddGradleConfiguration {
    Assert.notNull("instruction", configuration);
  }

  public String get() {
    return configuration;
  }
}
