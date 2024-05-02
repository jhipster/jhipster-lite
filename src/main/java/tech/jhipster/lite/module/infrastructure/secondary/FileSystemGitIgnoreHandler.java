package tech.jhipster.lite.module.infrastructure.secondary;

import java.util.function.Consumer;
import java.util.regex.Pattern;
import tech.jhipster.lite.module.domain.JHipsterProjectFilePath;
import tech.jhipster.lite.module.domain.git.GitIgnorePattern;
import tech.jhipster.lite.module.domain.git.GitIgnorePatterns;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;
import tech.jhipster.lite.module.domain.replacement.ContentReplacers;
import tech.jhipster.lite.module.domain.replacement.MandatoryFileReplacer;
import tech.jhipster.lite.module.domain.replacement.MandatoryReplacer;
import tech.jhipster.lite.module.domain.replacement.RegexNeedleAfterReplacer;
import tech.jhipster.lite.shared.error.domain.Assert;

class FileSystemGitIgnoreHandler {

  private static final Pattern END_OF_FILE = Pattern.compile("\\z", Pattern.MULTILINE);
  private static final JHipsterProjectFilePath GIT_IGNORE_FILE_PATH = new JHipsterProjectFilePath(".gitignore");
  private static final FileSystemReplacer fileReplacer = new FileSystemReplacer();

  public void handle(JHipsterProjectFolder projectFolder, GitIgnorePatterns gitIgnorePatterns) {
    Assert.notNull("projectFolder", projectFolder);
    Assert.notNull("gitIgnorePatterns", gitIgnorePatterns);

    gitIgnorePatterns.forEach(handleIgnorePattern(projectFolder));
  }

  private static Consumer<GitIgnorePattern> handleIgnorePattern(JHipsterProjectFolder projectFolder) {
    return gitIgnoreEntry -> {
      MandatoryReplacer replacer = new MandatoryReplacer(
        new RegexNeedleAfterReplacer((contentBeforeReplacement, newText) -> !contentBeforeReplacement.contains(newText), END_OF_FILE),
        gitIgnoreEntry.get()
      );
      fileReplacer.handle(projectFolder, ContentReplacers.of(new MandatoryFileReplacer(GIT_IGNORE_FILE_PATH, replacer)));
    };
  }
}
