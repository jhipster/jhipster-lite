package tech.jhipster.lite.module.infrastructure.secondary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import tech.jhipster.lite.module.domain.JHipsterProjectFilePath;
import tech.jhipster.lite.module.domain.git.GitIgnoreEntry;
import tech.jhipster.lite.module.domain.git.GitIgnorePatterns;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;
import tech.jhipster.lite.module.domain.replacement.ContentReplacers;
import tech.jhipster.lite.module.domain.replacement.MandatoryFileReplacer;
import tech.jhipster.lite.module.domain.replacement.MandatoryReplacer;
import tech.jhipster.lite.module.domain.replacement.RegexNeedleAfterReplacer;
import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.error.domain.GeneratorException;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

class FileSystemGitIgnoreHandler {

  private static final Pattern END_OF_FILE = Pattern.compile("\\z", Pattern.MULTILINE);
  private static final String GIT_IGNORE_FILE_PATH = ".gitignore";
  private static final FileSystemReplacer fileReplacer = new FileSystemReplacer();

  public void handle(JHipsterProjectFolder projectFolder, GitIgnorePatterns gitIgnorePatterns) {
    Assert.notNull("projectFolder", projectFolder);
    Assert.notNull("gitIgnorePatterns", gitIgnorePatterns);

    if (gitIgnorePatterns.isNotEmpty()) {
      createGitIgnoreFileIfNeeded(projectFolder);
    }
    gitIgnorePatterns.forEach(handleIgnorePattern(projectFolder));
  }

  @ExcludeFromGeneratedCodeCoverage(reason = "IOException is hard to test")
  private static void createGitIgnoreFileIfNeeded(JHipsterProjectFolder projectFolder) {
    Path gitIgnoreFilePath = projectFolder.filePath(GIT_IGNORE_FILE_PATH);

    if (Files.notExists(gitIgnoreFilePath)) {
      try {
        Files.createFile(gitIgnoreFilePath);
      } catch (IOException exception) {
        throw GeneratorException.technicalError(
          "Error creating %s file: %s".formatted(GIT_IGNORE_FILE_PATH, exception.getMessage()),
          exception
        );
      }
    }
  }

  private static Consumer<GitIgnoreEntry> handleIgnorePattern(JHipsterProjectFolder projectFolder) {
    return gitIgnoreEntry -> {
      MandatoryReplacer replacer = new MandatoryReplacer(
        new RegexNeedleAfterReplacer((contentBeforeReplacement, newText) -> !contentBeforeReplacement.contains(newText), END_OF_FILE),
        gitIgnoreEntry.get()
      );
      fileReplacer.handle(
        projectFolder,
        ContentReplacers.of(new MandatoryFileReplacer(new JHipsterProjectFilePath(GIT_IGNORE_FILE_PATH), replacer))
      );
    };
  }
}
