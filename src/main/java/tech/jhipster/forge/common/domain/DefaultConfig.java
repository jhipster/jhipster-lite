package tech.jhipster.forge.common.domain;

import java.util.Map;
import java.util.Optional;

public class DefaultConfig {

  static Map<String, String> defaultMap = Map.of("baseName", "jhipster", "projectName", "JHipster Project", "nodeVersion", "14.17.6");

  private DefaultConfig() {}

  public static Optional<String> get(String key) {
    return Optional.ofNullable(defaultMap.get(key));
  }
}
