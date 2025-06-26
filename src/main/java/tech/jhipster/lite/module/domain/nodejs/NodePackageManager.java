package tech.jhipster.lite.module.domain.nodejs;

import java.util.stream.Stream;
import tech.jhipster.lite.shared.error.domain.Assert;

public enum NodePackageManager {
  NPM("npm", "npm"),
  PNPM("pnpm", "pnpm");

  private final String command;
  private final String propertyKey;

  NodePackageManager(String command, String propertyKey) {
    Assert.notNull(command, "command");
    Assert.notNull(propertyKey, "propertyKey");

    this.command = command;
    this.propertyKey = propertyKey;
  }

  public static NodePackageManager fromPropertyKey(String input) {
    return Stream.of(values()).filter(configFormat -> configFormat.propertyKey.equals(input)).findFirst().orElseThrow();
  }

  public String propertyKey() {
    return propertyKey;
  }

  public String command() {
    return command;
  }

  @Override
  public String toString() {
    return command();
  }
}
