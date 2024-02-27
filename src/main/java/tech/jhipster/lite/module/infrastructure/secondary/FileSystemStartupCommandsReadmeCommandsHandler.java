package tech.jhipster.lite.module.infrastructure.secondary;

import static tech.jhipster.lite.module.domain.JHipsterModule.lineBeforeText;
import static tech.jhipster.lite.module.domain.JHipsterModule.path;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.module.domain.JHipsterProjectFilePath;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;
import tech.jhipster.lite.module.domain.replacement.ContentReplacers;
import tech.jhipster.lite.module.domain.replacement.OptionalFileReplacer;
import tech.jhipster.lite.module.domain.replacement.OptionalReplacer;
import tech.jhipster.lite.module.domain.replacement.TextNeedleBeforeReplacer;
import tech.jhipster.lite.module.domain.startupcommand.JHipsterStartupCommand;
import tech.jhipster.lite.module.domain.startupcommand.JHipsterStartupCommands;
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

  public void handle(JHipsterProjectFolder projectFolder, JHipsterStartupCommands commands) {
    Assert.notNull("projectFolder", projectFolder);
    Assert.notNull("commands", commands);

    if (!commands.get().isEmpty()) {
      handleCommandsForProjectType(projectFolder, commands);
    }
  }

  private void handleCommandsForProjectType(JHipsterProjectFolder projectFolder, JHipsterStartupCommands commands) {
    commands.get().forEach(command -> addCommandToReadme(projectFolder, command));
  }

  private void addCommandToReadme(JHipsterProjectFolder projectFolder, JHipsterStartupCommand command) {
    String replacedTemplate = BASH_TEMPLATE.replace("{{command}}", command.commandLine().get());
    OptionalReplacer replacer = new OptionalReplacer(JHIPSTER_STARTUP_COMMAND_SECTION_NEEDLE, replacedTemplate);
    fileReplacer.handle(projectFolder, ContentReplacers.of(new OptionalFileReplacer(README, replacer)));
  }
}
