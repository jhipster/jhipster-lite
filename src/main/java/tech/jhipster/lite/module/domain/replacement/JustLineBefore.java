package tech.jhipster.lite.module.domain.replacement;

import static tech.jhipster.lite.module.domain.replacement.JHipsterModuleReplacements.*;

import tech.jhipster.lite.error.domain.Assert;

public record JustLineBefore(ElementMatcher element) implements PositionalMatcher {
  public JustLineBefore {
    Assert.notNull("element", element);
  }

  @Override
  public String buildReplacement(String value) {
    Assert.notBlank("value", value);
    return value.concat(LF).concat(element().searchMatcher());
  }
}
