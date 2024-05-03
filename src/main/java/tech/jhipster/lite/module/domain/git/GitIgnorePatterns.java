package tech.jhipster.lite.module.domain.git;

import java.util.Collection;
import java.util.function.Consumer;
import tech.jhipster.lite.shared.error.domain.Assert;

public record GitIgnorePatterns(Collection<GitIgnoreEntry> patterns) {
  public GitIgnorePatterns {
    Assert.field("patterns", patterns).notNull().noNullElement();
  }

  public void forEach(Consumer<GitIgnoreEntry> consumer) {
    Assert.notNull("consumer", consumer);

    patterns.forEach(consumer);
  }

  public boolean isNotEmpty() {
    return !patterns.isEmpty();
  }

  @Override
  public String toString() {
    return patterns.toString();
  }
}
