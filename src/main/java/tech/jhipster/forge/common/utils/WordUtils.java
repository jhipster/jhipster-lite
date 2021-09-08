package tech.jhipster.forge.common.utils;

import com.google.common.base.CaseFormat;
import tech.jhipster.forge.error.domain.Assert;

public class WordUtils {

  private WordUtils() {}

  public static String kebabCase(String value) {
    Assert.notBlank("value", value);

    return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, value);
  }
}
