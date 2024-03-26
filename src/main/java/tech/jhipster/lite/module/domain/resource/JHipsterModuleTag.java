package tech.jhipster.lite.module.domain.resource;

import tech.jhipster.lite.shared.error.domain.Assert;

public record JHipsterModuleTag(String tag) {
  public JHipsterModuleTag {
    Assert.field("tag", tag).notNull().noWhitespace().maxLength(15).urlSafeSingleWord(() -> new InvalidJHipsterModuleTagException(tag));
  }

  public String get() {
    return tag();
  }
}
