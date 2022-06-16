package tech.jhipster.lite.generator.module.domain.replacement;

import static tech.jhipster.lite.generator.module.domain.replacement.JHipsterModuleReplacements.LF;

import tech.jhipster.lite.error.domain.Assert;

public record JustLineBefore(ElementMatcher element) implements PositionalMatcher {
  public JustLineBefore {
    Assert.notNull("element", element);
  }
  public String updateReplacement(String value) {
    return value.concat(LF).concat(element.searchMatcher());
  }
}
