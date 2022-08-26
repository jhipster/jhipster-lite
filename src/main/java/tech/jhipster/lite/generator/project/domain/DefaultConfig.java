package tech.jhipster.lite.generator.project.domain;

import static tech.jhipster.lite.common.domain.WordUtils.*;

import java.util.Map;
import java.util.Optional;

public class DefaultConfig {

  public static final String BASE_NAME = "baseName";
  public static final String PROJECT_NAME = "projectName";
  public static final String PACKAGE_NAME = "packageName";
  public static final String INDENT_SIZE = "indentSize";

  public static final String DEFAULT_PACKAGE_NAME = "com.mycompany.myapp";
  public static final String DEFAULT_BASE_NAME = "jhipster";
  public static final String DEFAULT_PROJECT_NAME = "JHipster Project";

  //@formatter:off
  public static final Map<String, Object> defaultMap = Map.of(
    BASE_NAME, DEFAULT_BASE_NAME,
    PROJECT_NAME, DEFAULT_PROJECT_NAME,
    PACKAGE_NAME, DEFAULT_PACKAGE_NAME,
    INDENT_SIZE, DEFAULT_INDENTATION
  );

  //@formatter:on

  private DefaultConfig() {}

  public static Optional<Object> get(String key) {
    return Optional.ofNullable(defaultMap.get(key));
  }
}
