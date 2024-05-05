package tech.jhipster.lite.module.domain.gitignore;

import java.util.Collection;
import java.util.function.Consumer;
import tech.jhipster.lite.shared.error.domain.Assert;

public record GitIgnore(Collection<GitIgnoreEntry> entries) {
  public GitIgnore {
    Assert.field("entries", entries).notNull().noNullElement();
  }

  public void forEach(Consumer<GitIgnoreEntry> consumer) {
    Assert.notNull("consumer", consumer);

    entries.forEach(consumer);
  }

  public boolean isNotEmpty() {
    return !entries.isEmpty();
  }

  @Override
  public String toString() {
    return entries.toString();
  }
}
