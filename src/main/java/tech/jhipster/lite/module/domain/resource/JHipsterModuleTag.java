package tech.jhipster.lite.module.domain.resource;

import java.util.List;
import tech.jhipster.lite.error.domain.Assert;

public record JHipsterModuleTag(String tag) {
  public JHipsterModuleTag {
    Assert.field("tag", tag).notBlank().maxLength(50);
  }

  public String get() {
    return tag();
  }

  public List<String> list() {
    return List.of(tag());
  }
}
