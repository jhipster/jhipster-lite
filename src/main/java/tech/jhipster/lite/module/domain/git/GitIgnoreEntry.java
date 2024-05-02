package tech.jhipster.lite.module.domain.git;

import tech.jhipster.lite.shared.error.domain.Assert;

public sealed interface GitIgnoreEntry permits GitIgnoreEntry.GitIgnorePattern, GitIgnoreEntry.GitIgnoreComment {
  String get();

  record GitIgnorePattern(String value) implements GitIgnoreEntry {
    public GitIgnorePattern {
      Assert.notBlank("value", value);
    }

    public String get() {
      return value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

  record GitIgnoreComment(String value) implements GitIgnoreEntry {
    public GitIgnoreComment(String value) {
      Assert.notBlank("value", value);

      if (!value.startsWith("#")) {
        this.value = "# " + value;
        return;
      }
      this.value = value;
    }

    public String get() {
      return value;
    }

    @Override
    public String toString() {
      return value;
    }
  }
}
