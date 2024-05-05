package tech.jhipster.lite.module.domain.gitignore;

import java.util.ArrayList;
import java.util.Collection;
import tech.jhipster.lite.module.domain.JHipsterModule.JHipsterModuleBuilder;
import tech.jhipster.lite.module.domain.gitignore.GitIgnoreEntry.GitIgnoreComment;
import tech.jhipster.lite.module.domain.gitignore.GitIgnoreEntry.GitIgnorePattern;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class JHipsterModuleGitIgnore {

  private final GitIgnore ignorePatterns;

  private JHipsterModuleGitIgnore(JHipsterModuleGitIgnoreBuilder builder) {
    this.ignorePatterns = new GitIgnore(builder.ignorePatterns);
  }

  public GitIgnore ignorePatterns() {
    return ignorePatterns;
  }

  public static JHipsterModuleGitIgnoreBuilder builder(JHipsterModuleBuilder parentModuleBuilder) {
    return new JHipsterModuleGitIgnoreBuilder(parentModuleBuilder);
  }

  public static final class JHipsterModuleGitIgnoreBuilder {

    private final JHipsterModuleBuilder parentModuleBuilder;
    private final Collection<GitIgnoreEntry> ignorePatterns = new ArrayList<>();

    private JHipsterModuleGitIgnoreBuilder(JHipsterModuleBuilder parentModuleBuilder) {
      Assert.notNull("module", parentModuleBuilder);

      this.parentModuleBuilder = parentModuleBuilder;
    }

    /**
     * Declare a pattern to be added to the {@code .gitignore} file.
     */
    public JHipsterModuleGitIgnoreBuilder pattern(GitIgnorePattern pattern) {
      Assert.notNull("pattern", pattern);
      ignorePatterns.add(pattern);

      return this;
    }

    /**
     * Declare a pattern to be added to the {@code .gitignore} file.
     */
    public JHipsterModuleGitIgnoreBuilder pattern(String pattern) {
      return pattern(new GitIgnorePattern(pattern));
    }

    /**
     * Declare a comment to be added to the {@code .gitignore} file.
     */
    public JHipsterModuleGitIgnoreBuilder comment(GitIgnoreComment comment) {
      Assert.notNull("comment", comment);
      ignorePatterns.add(comment);

      return this;
    }

    /**
     * Declare a comment to be added to the {@code .gitignore} file.
     */
    public JHipsterModuleGitIgnoreBuilder comment(String comment) {
      return comment(new GitIgnoreComment(comment));
    }

    public JHipsterModuleBuilder and() {
      return parentModuleBuilder;
    }

    public JHipsterModuleGitIgnore build() {
      return new JHipsterModuleGitIgnore(this);
    }
  }
}
