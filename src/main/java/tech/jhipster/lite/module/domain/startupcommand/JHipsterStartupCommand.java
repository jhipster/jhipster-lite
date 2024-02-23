package tech.jhipster.lite.module.domain.startupcommand;

import tech.jhipster.lite.shared.error.domain.Assert;

public record JHipsterStartupCommand(StartupCommandLine commandLine, StartupCommandType type) {
  public JHipsterStartupCommand {
    Assert.notNull("commandLine", commandLine);
    Assert.notNull("type", type);
  }
}
