package tech.jhipster.forge.common.utils;

import com.google.common.base.CaseFormat;
import tech.jhipster.forge.error.domain.Assert;

public class WordUtils {

  public static final int DEFAULT_INDENTATION = 2;

  private WordUtils() {}

  public static String kebabCase(String value) {
    Assert.notBlank("value", value);

    return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, value);
  }

  public static String indent(int times) {
    return indent(times, DEFAULT_INDENTATION);
  }

  public static String indent(int times, int spaceNumber) {
    Assert.notLowerThan("times", times, 1);
    Assert.notLowerThan("spaceNumber", spaceNumber, 1);
    return " ".repeat(times * spaceNumber);
  }
}
