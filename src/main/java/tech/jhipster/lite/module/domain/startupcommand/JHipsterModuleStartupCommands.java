package tech.jhipster.lite.module.domain.startupcommand;

import java.util.ArrayList;
import java.util.Collection;
import tech.jhipster.lite.module.domain.JHipsterModule.JHipsterModuleBuilder;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class JHipsterModuleStartupCommands {

  private final JHipsterStartupCommands commands;

  private JHipsterModuleStartupCommands(JHipsterModuleStartupCommandsBuilder builder) {
    commands = new JHipsterStartupCommands(builder.commands);
  }

  public static JHipsterModuleStartupCommandsBuilder builder(JHipsterModuleBuilder module) {
    return new JHipsterModuleStartupCommandsBuilder(module);
  }

  public JHipsterStartupCommands commands() {
    return commands;
  }

  public static final class JHipsterModuleStartupCommandsBuilder {

    private final JHipsterModuleBuilder module;
    private Collection<JHipsterStartupCommand> commands = new ArrayList<>();

    private JHipsterModuleStartupCommandsBuilder(JHipsterModuleBuilder module) {
      Assert.notNull("module", module);

      this.module = module;
    }

    public JHipsterModuleStartupCommandsBuilder docker(String commandLine) {
      addCommand(commandLine, StartupCommandType.DOCKER_COMPOSE);
      return this;
    }

    public JHipsterModuleStartupCommandsBuilder maven(String commandLine) {
      addCommand(commandLine, StartupCommandType.MAVEN);
      return this;
    }

    public JHipsterModuleStartupCommandsBuilder gradle(String commandLine) {
      addCommand(commandLine, StartupCommandType.GRADLE);
      return this;
    }

    public JHipsterModuleBuilder and() {
      return module;
    }

    public JHipsterModuleStartupCommands build() {
      return new JHipsterModuleStartupCommands(this);
    }

    private void addCommand(String commandLine, StartupCommandType commandType) {
      Assert.notNull("commandLine", commandLine);
      this.commands.add(new JHipsterStartupCommand(new StartupCommandLine(commandLine), commandType));
    }
  }
}
