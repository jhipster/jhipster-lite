package tech.jhipster.lite.generator.module.domain.replacement;

interface PositionalMatcher {
  ElementMatcher element();

  String buildReplacement(String value);
}
