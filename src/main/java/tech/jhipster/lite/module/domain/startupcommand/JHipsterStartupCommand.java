package tech.jhipster.lite.module.domain.startupcommand;

import tech.jhipster.lite.shared.error.domain.Assert;

@SuppressWarnings("java:S1192")
public sealed interface JHipsterStartupCommand
  permits
    JHipsterStartupCommand.DockerStartupCommandLine,
    JHipsterStartupCommand.GradleStartupCommandLine,
    JHipsterStartupCommand.MavenStartupCommandLine {
  StartupCommandLine commandLine();

  record MavenStartupCommandLine(StartupCommandLine commandLine) implements JHipsterStartupCommand {
    public MavenStartupCommandLine {
      Assert.notNull("commandLine", commandLine);
    }

    public MavenStartupCommandLine(String commandLine) {
      this(new StartupCommandLine(commandLine));
    }
  }

  record GradleStartupCommandLine(StartupCommandLine commandLine) implements JHipsterStartupCommand {
    public GradleStartupCommandLine {
      Assert.notNull("commandLine", commandLine);
    }

    public GradleStartupCommandLine(String commandLine) {
      this(new StartupCommandLine(commandLine));
    }
  }

  record DockerStartupCommandLine(StartupCommandLine commandLine) implements JHipsterStartupCommand {
    public DockerStartupCommandLine {
      Assert.notNull("commandLine", commandLine);
    }

    public DockerStartupCommandLine(String commandLine) {
      this(new StartupCommandLine(commandLine));
    }
  }
}
