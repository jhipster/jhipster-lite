package tech.jhipster.lite.module.domain.startupcommand;

import java.util.ArrayList;
import java.util.Collection;
import tech.jhipster.lite.module.domain.JHipsterModule.JHipsterModuleBuilder;
import tech.jhipster.lite.module.domain.startupcommand.JHipsterStartupCommand.DockerStartupCommandLine;
import tech.jhipster.lite.module.domain.startupcommand.JHipsterStartupCommand.GradleStartupCommandLine;
import tech.jhipster.lite.module.domain.startupcommand.JHipsterStartupCommand.MavenStartupCommandLine;
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
    private final Collection<JHipsterStartupCommand> commands = new ArrayList<>();

    private JHipsterModuleStartupCommandsBuilder(JHipsterModuleBuilder module) {
      Assert.notNull("module", module);

      this.module = module;
    }

    public JHipsterModuleStartupCommandsBuilder docker(String dockerComposeFile) {
      commands.add(new DockerStartupCommandLine(new DockerComposeFile(dockerComposeFile)));
      return this;
    }

    public JHipsterModuleStartupCommandsBuilder maven(String commandLine) {
      commands.add(new MavenStartupCommandLine(commandLine));
      return this;
    }

    public JHipsterModuleStartupCommandsBuilder gradle(String commandLine) {
      commands.add(new GradleStartupCommandLine(commandLine));
      return this;
    }

    public JHipsterModuleBuilder and() {
      return module;
    }

    public JHipsterModuleStartupCommands build() {
      return new JHipsterModuleStartupCommands(this);
    }
  }
}
