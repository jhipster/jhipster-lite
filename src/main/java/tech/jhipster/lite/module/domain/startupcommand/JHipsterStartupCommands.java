package tech.jhipster.lite.module.domain.startupcommand;

import java.util.Collection;
import tech.jhipster.lite.shared.collection.domain.JHipsterCollections;

public record JHipsterStartupCommands(Collection<? extends JHipsterStartupCommand> commands) {
  public JHipsterStartupCommands(Collection<? extends JHipsterStartupCommand> commands) {
    this.commands = JHipsterCollections.immutable(commands);
  }

  public Collection<? extends JHipsterStartupCommand> get() {
    return commands;
  }
}
