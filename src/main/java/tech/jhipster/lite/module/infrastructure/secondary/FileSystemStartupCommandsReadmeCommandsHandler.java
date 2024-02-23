package tech.jhipster.lite.module.infrastructure.secondary;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import java.nio.file.Files;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;
import tech.jhipster.lite.module.domain.JHipsterProjectFilePath;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;
import tech.jhipster.lite.module.domain.replacement.*;
import tech.jhipster.lite.module.domain.startupcommand.JHipsterStartupCommand;
import tech.jhipster.lite.module.domain.startupcommand.JHipsterStartupCommands;
import tech.jhipster.lite.module.domain.startupcommand.StartupCommandType;
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

  private final Map<StartupCommandType, Set<ProjectType>> commandTypeToProjectTypesMap = new EnumMap<>(StartupCommandType.class);

  public FileSystemStartupCommandsReadmeCommandsHandler() {
    commandTypeToProjectTypesMap.put(StartupCommandType.MAVEN, Set.of(ProjectType.MAVEN));
    commandTypeToProjectTypesMap.put(StartupCommandType.GRADLE, Set.of(ProjectType.GRADLE));
    commandTypeToProjectTypesMap.put(StartupCommandType.DOCKER_COMPOSE, Set.of(ProjectType.MAVEN, ProjectType.GRADLE));
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
    for (JHipsterStartupCommand command : commands.get()) {
      if (shouldAddCommand(command, projectType)) {
        addCommandToReadme(projectFolder, command);
      }
    }
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

  private boolean shouldAddCommand(JHipsterStartupCommand command, ProjectType projectType) {
    Set<ProjectType> validProjectTypes = commandTypeToProjectTypesMap.getOrDefault(command.type(), Set.of());
    return validProjectTypes.contains(projectType);
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
