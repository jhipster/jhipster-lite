package tech.jhipster.lite.generator.module.domain.replacement;

import static tech.jhipster.lite.generator.module.domain.replacement.JHipsterModuleReplacements.LF;

import tech.jhipster.lite.error.domain.Assert;

public record JustLineAfter(ElementMatcher element) implements PositionalMatcher {
  public JustLineAfter {
    Assert.notNull("element", element);
  }
  public String updateReplacement(String value) {
    return element.searchMatcher().concat(LF).concat(value);
  }
}
