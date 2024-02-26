package tech.jhipster.lite.module.infrastructure.secondary;

import static tech.jhipster.lite.module.domain.JHipsterModule.lineBeforeText;
import static tech.jhipster.lite.module.domain.JHipsterModule.path;

import java.nio.file.Files;
import java.util.function.Predicate;
import org.springframework.stereotype.Service;
import tech.jhipster.lite.module.domain.JHipsterProjectFilePath;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;
import tech.jhipster.lite.module.domain.replacement.ContentReplacers;
import tech.jhipster.lite.module.domain.replacement.OptionalFileReplacer;
import tech.jhipster.lite.module.domain.replacement.OptionalReplacer;
import tech.jhipster.lite.module.domain.replacement.TextNeedleBeforeReplacer;
import tech.jhipster.lite.module.domain.startupcommand.DockerComposeStartupCommandLine;
import tech.jhipster.lite.module.domain.startupcommand.GradleStartupCommandLine;
import tech.jhipster.lite.module.domain.startupcommand.JHipsterStartupCommand;
import tech.jhipster.lite.module.domain.startupcommand.JHipsterStartupCommands;
import tech.jhipster.lite.module.domain.startupcommand.MavenStartupCommandLine;
import tech.jhipster.lite.shared.error.domain.Assert;

@Service
class FileSystemStartupCommandsReadmeCommandsHandler {

  private static final JHipsterProjectFilePath README = path("README.md");
  private static final TextNeedleBeforeReplacer JHIPSTER_STARTUP_COMMAND_SECTION_NEEDLE = lineBeforeText(
    "\n<!-- jhipster-needle-startupCommand -->"
  );
  private static final String BASH_TEMPLATE =
    """
    ```bash
    {{command}}
    ```
    """;
  private final FileSystemReplacer fileReplacer = new FileSystemReplacer();

  private enum ProjectType {
    MAVEN,
    GRADLE,
    UNKNOWN,
  }

  public void handle(JHipsterProjectFolder projectFolder, JHipsterStartupCommands commands) {
    Assert.notNull("projectFolder", projectFolder);
    Assert.notNull("commands", commands);

    if (!commands.get().isEmpty()) {
      handleCommandsForProjectType(projectFolder, commands);
    }
  }

  private void handleCommandsForProjectType(JHipsterProjectFolder projectFolder, JHipsterStartupCommands commands) {
    ProjectType projectType = identifyProjectType(projectFolder);
    commands.get().stream().filter(shouldAddCommand(projectType)).forEach(command -> addCommandToReadme(projectFolder, command));
  }

  private ProjectType identifyProjectType(JHipsterProjectFolder projectFolder) {
    ProjectType projectType = ProjectType.UNKNOWN;
    if (fileExists(projectFolder, "pom.xml")) {
      projectType = ProjectType.MAVEN;
    } else if (fileExists(projectFolder, "build.gradle.kts")) {
      projectType = ProjectType.GRADLE;
    }
    return projectType;
  }

  private Predicate<JHipsterStartupCommand> shouldAddCommand(ProjectType projectType) {
    return command ->
      switch (command) {
        case MavenStartupCommandLine __ -> projectType == ProjectType.MAVEN;
        case GradleStartupCommandLine __ -> projectType == ProjectType.GRADLE;
        case DockerComposeStartupCommandLine __ -> true;
      };
  }

  private void addCommandToReadme(JHipsterProjectFolder projectFolder, JHipsterStartupCommand command) {
    String replacedTemplate = BASH_TEMPLATE.replace("{{command}}", command.commandLine().get());
    OptionalReplacer replacer = new OptionalReplacer(JHIPSTER_STARTUP_COMMAND_SECTION_NEEDLE, replacedTemplate);
    fileReplacer.handle(projectFolder, ContentReplacers.of(new OptionalFileReplacer(README, replacer)));
  }

  private boolean fileExists(JHipsterProjectFolder projectFolder, String fileName) {
    return Files.exists(projectFolder.filePath(fileName));
  }
}
