package tech.jhipster.lite.module.domain.properties;

import org.apache.commons.lang3.StringUtils;
import tech.jhipster.lite.shared.error.domain.Assert;

public record JHipsterProjectBaseName(String name) {
  private static final String DEFAULT_NAME = "jhipster";

  public static final JHipsterProjectBaseName DEFAULT = new JHipsterProjectBaseName(DEFAULT_NAME);

  public JHipsterProjectBaseName(String name) {
    this.name = buildName(name);
  }

  private static String buildName(String name) {
    if (StringUtils.isBlank(name)) {
      return DEFAULT_NAME;
    }

    Assert.field("baseName", name).namePattern(() -> new InvalidProjectBaseNameException());

    return name;
  }

  public String get() {
    return name();
  }

  public String uncapitalized() {
    return StringUtils.uncapitalize(name());
  }

  public String capitalized() {
    return StringUtils.capitalize(name());
  }

  public String kebabCase() {
    return StringUtils.uncapitalize(name()).replaceAll("([A-Z])", "-$1").toLowerCase();
  }
}
