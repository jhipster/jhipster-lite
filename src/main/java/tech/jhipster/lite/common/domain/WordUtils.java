package tech.jhipster.lite.common.domain;

import com.google.common.base.CaseFormat;
import tech.jhipster.lite.error.domain.Assert;

public class WordUtils {

  public static final String LF = "\n";
  public static final String CRLF = "\r\n";

  public static final int DEFAULT_INDENTATION = 2;
  public static final String DQ = "\"";

  public static final String OB = "\\{";
  public static final String CB = "\\}";

  private WordUtils() {}

  public static String kebabCase(String value) {
    Assert.notBlank("value", value);

    return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, value);
  }

  public static String upperFirst(String value) {
    Assert.notBlank("value", value);

    StringBuilder result = new StringBuilder();
    result.append(value.substring(0, 1).toUpperCase());
    result.append(value.substring(1));
    return result.toString();
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
