package tech.jhipster.lite.module.domain.startupcommand;

import java.util.Collection;
import tech.jhipster.lite.shared.collection.domain.JHipsterCollections;

public record JHipsterStartupCommands(Collection<JHipsterStartupCommand> commands) {
  public JHipsterStartupCommands(Collection<JHipsterStartupCommand> commands) {
    this.commands = JHipsterCollections.immutable(commands);
  }

  public Collection<JHipsterStartupCommand> get() {
    return commands;
  }
}
