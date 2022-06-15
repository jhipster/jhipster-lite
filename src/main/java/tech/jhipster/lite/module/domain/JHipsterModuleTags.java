package tech.jhipster.lite.generator.module.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.module.domain.JHipsterModule.JHipsterModuleBuilder;

public class JHipsterModuleTags {

  private final Collection<JHipsterModuleTag> tags;

  private JHipsterModuleTags(JHipsterModuleTagsBuilder builder) {
    tags = builder.tags;
  }

  static JHipsterModuleTagsBuilder builder(JHipsterModuleBuilder module) {
    return new JHipsterModuleTagsBuilder(module);
  }

  public Collection<JHipsterModuleTag> get() {
    return Collections.unmodifiableCollection(tags);
  }

  public static class JHipsterModuleTagsBuilder {

    private final JHipsterModuleBuilder module;
    private final Collection<JHipsterModuleTag> tags = new ArrayList<>();

    private JHipsterModuleTagsBuilder(JHipsterModuleBuilder module) {
      Assert.notNull("module", module);

      this.module = module;
    }

    public JHipsterModuleTagsBuilder add(JHipsterModuleTag tag) {
      Assert.notNull("tag", tag);
      tags.add(tag);

      return this;
    }

    public JHipsterModuleTagsBuilder add(String tag) {
      tags.add(new JHipsterModuleTag(tag));

      return this;
    }

    public JHipsterModuleBuilder and() {
      return module;
    }

    public JHipsterModuleTags build() {
      return new JHipsterModuleTags(this);
    }
  }
}
