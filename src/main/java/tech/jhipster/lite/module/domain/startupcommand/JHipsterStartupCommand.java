package tech.jhipster.lite.module.domain.startupcommand;

import tech.jhipster.lite.shared.error.domain.Assert;

@SuppressWarnings("java:S1192")
public sealed interface JHipsterStartupCommand
  permits
    JHipsterStartupCommand.DockerStartupCommandLine,
    JHipsterStartupCommand.GradleStartupCommandLine,
    JHipsterStartupCommand.MavenStartupCommandLine {
  StartupCommandLine commandLine();

  record MavenStartupCommandLine(String commandLineParameters) implements JHipsterStartupCommand {
    public MavenStartupCommandLine {
      Assert.notNull("commandLineParameters", commandLineParameters);
    }

    @Override
    public StartupCommandLine commandLine() {
      return new StartupCommandLine("./mvnw " + commandLineParameters.trim());
    }
  }

  record GradleStartupCommandLine(String commandLineParameters) implements JHipsterStartupCommand {
    public GradleStartupCommandLine {
      Assert.notNull("commandLineParameters", commandLineParameters);
    }

    @Override
    public StartupCommandLine commandLine() {
      return new StartupCommandLine("./gradlew " + commandLineParameters.trim());
    }
  }

  record DockerStartupCommandLine(DockerComposeFile dockerComposeFile) implements JHipsterStartupCommand {
    public DockerStartupCommandLine {
      Assert.notNull("dockerComposeFile", dockerComposeFile);
    }

    @Override
    public StartupCommandLine commandLine() {
      return new StartupCommandLine("docker compose -f %s up -d".formatted(dockerComposeFile.path()));
    }
  }
}
