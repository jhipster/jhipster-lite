package tech.jhipster.lite.generator.client.angular.common.domain;

import java.util.List;

public class AngularCommon {

  protected static final String IMPORT_REGEX = "(^import[\\s]+[^;]+;)";
  protected static final String EXISTING_IMPORT_PATTERN = "^(import[\\s]+\\{[^\\}]+)\\} from '[%s]+';";

  protected static final List<String> DECORATOR_REGEX_LIST = List.of("(^@[A-Z]{1}[\\w]+\\(\\{)");

  protected static final String DECLARATIONS_REGEX = "^([\\s]+declarations:[\\s]+\\[[^\\]]+\\][\\s,]*)";

  protected static final List<String> DECLARATIONS_WITH_ARRAY_VALUES_REGEX_LIST = List.of(
    "^([\\s]+declarations:[\\s]+\\[[^\\]]+)\\]",
    "^([\\s]+declarations:[\\s]+\\[)\\]"
  );

  protected static final List<String> PROVIDERS_WITH_ARRAY_VALUES_REGEX_LIST = List.of(
    "^([\\s]+providers:[\\s]+\\[[^\\]]+)\\]",
    "^([\\s]+providers:[\\s]+\\[)\\]"
  );

  protected static final List<String> ENV_VARIABLES_WITH_VALUES_REGEX_LIST = List.of(
    "(export const environment = [^(\\};)]+[\\}]?[\\s,]*)\\};"
  );

  protected static final String TEST_REGEX_FORMAT = "([\\s]+it\\('%s',[^}]+}\\);)";

  protected static final String COMA = ",";
  protected static final String O_BRACKET = "[";
  protected static final String C_BRACKET = "]";

  private AngularCommon() {
    // Cannot be instantiated
  }
}
