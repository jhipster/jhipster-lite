package tech.jhipster.lite.module.domain.replacement;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import tech.jhipster.lite.module.domain.JHipsterModuleContext;
import tech.jhipster.lite.shared.error.domain.Assert;

public record ContentReplacers(JHipsterModuleContext context, Collection<? extends ContentReplacer> replacers) {
  public ContentReplacers {
    Assert.notNull("context", context);
    Assert.field("replacers", replacers).notNull().noNullElement();
  }

  public static ContentReplacers of(JHipsterModuleContext context, ContentReplacer... replacers) {
    Assert.notNull("context", context);
    Assert.field("replacers", replacers).notNull();
    return new ContentReplacers(context, List.of(replacers));
  }

  public void forEach(Consumer<ContentReplacer> action) {
    replacers().forEach(action);
  }
}
