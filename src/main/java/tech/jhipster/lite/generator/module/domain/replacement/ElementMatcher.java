package tech.jhipster.lite.generator.module.domain.replacement;

import java.util.function.BiFunction;

public interface ElementMatcher {
  boolean notMatchIn(String content);

  BiFunction<String, String, String> replacer();

  String searchMatcher();
}
