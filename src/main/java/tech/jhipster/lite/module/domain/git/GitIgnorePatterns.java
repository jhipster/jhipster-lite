package tech.jhipster.lite.module.domain.git;

import java.util.Collection;
import java.util.function.Consumer;
import tech.jhipster.lite.shared.error.domain.Assert;

public record GitIgnorePatterns(Collection<GitIgnorePattern> patterns) {
  public GitIgnorePatterns {
    Assert.field("patterns", patterns).notNull().noNullElement();
  }

  public void forEach(Consumer<GitIgnorePattern> consumer) {
    Assert.notNull("consumer", consumer);

    patterns.forEach(consumer);
  }
}
