package tech.jhipster.forge.common.domain;

import java.util.regex.Pattern;
import tech.jhipster.forge.error.domain.Assert;
import tech.jhipster.forge.error.domain.UnauthorizedValueException;

public class CheckConfig {

  public static Pattern PATTERN_BASENAME = Pattern.compile("^[a-zA-Z0-9_]+$");

  private CheckConfig() {}

  public static void validBaseName(String baseName) {
    Assert.notBlank("baseName", baseName);

    if (!PATTERN_BASENAME.matcher(baseName).find()) {
      throw new UnauthorizedValueException("The baseName cannot contain special characters or a blank space");
    }
  }
}
