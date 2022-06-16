package tech.jhipster.lite.generator.module.domain.replacement;

import tech.jhipster.lite.error.domain.Assert;

public record JustAfter(ElementMatcher element) implements PositionalMatcher {
  public JustAfter {
    Assert.notNull("element", element);
  }
  public String updateReplacement(String value) {
    return element.searchMatcher().concat(value);
  }
}
