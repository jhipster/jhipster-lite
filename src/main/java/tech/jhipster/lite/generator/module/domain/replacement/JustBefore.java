package tech.jhipster.lite.generator.module.domain.replacement;

import tech.jhipster.lite.error.domain.Assert;

public record JustBefore(ElementMatcher element) implements PositionalMatcher {
  public JustBefore {
    Assert.notNull("element", element);
  }
  public String updateReplacement(String value) {
    return value.concat(element.searchMatcher());
  }
}
