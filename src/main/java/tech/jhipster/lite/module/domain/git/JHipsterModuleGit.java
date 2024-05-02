package tech.jhipster.lite.module.domain.git;

import java.util.ArrayList;
import java.util.Collection;
import tech.jhipster.lite.module.domain.JHipsterModule.JHipsterModuleBuilder;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class JHipsterModuleGit {

  private final GitIgnorePatterns ignorePatterns;

  private JHipsterModuleGit(JHipsterModuleGitBuilder builder) {
    this.ignorePatterns = new GitIgnorePatterns(builder.ignorePatterns);
  }

  public GitIgnorePatterns ignorePatterns() {
    return ignorePatterns;
  }

  public static JHipsterModuleGitBuilder builder(JHipsterModuleBuilder parentModuleBuilder) {
    return new JHipsterModuleGitBuilder(parentModuleBuilder);
  }

  public static final class JHipsterModuleGitBuilder {

    private final JHipsterModuleBuilder parentModuleBuilder;
    private final Collection<GitIgnorePattern> ignorePatterns = new ArrayList<>();

    private JHipsterModuleGitBuilder(JHipsterModuleBuilder parentModuleBuilder) {
      Assert.notNull("module", parentModuleBuilder);

      this.parentModuleBuilder = parentModuleBuilder;
    }

    public JHipsterModuleGitBuilder ignore(GitIgnorePattern ignorePattern) {
      Assert.notNull("ignorePattern", ignorePattern);
      ignorePatterns.add(ignorePattern);

      return this;
    }

    public JHipsterModuleGitBuilder ignore(String ignorePattern) {
      return ignore(new GitIgnorePattern(ignorePattern));
    }

    public JHipsterModuleBuilder and() {
      return parentModuleBuilder;
    }

    public JHipsterModuleGit build() {
      return new JHipsterModuleGit(this);
    }
  }
}
