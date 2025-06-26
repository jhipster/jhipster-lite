package tech.jhipster.lite.module.domain.nodejs;

import java.util.stream.Stream;
import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public enum NodePackageManager {
  NPM("npm", "npm", new NodePackageName("npm")),
  PNPM("pnpm", "pnpm", new NodePackageName("pnpm"));

  private final String command;
  private final String propertyKey;
  private final NodePackageName packageName;

  NodePackageManager(String command, String propertyKey, NodePackageName packageName) {
    Assert.notNull("command", command);
    Assert.notNull("propertyKey", propertyKey);
    Assert.notNull("packageName", packageName);

    this.command = command;
    this.propertyKey = propertyKey;
    this.packageName = packageName;
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
  @ExcludeFromGeneratedCodeCoverage
  public String toString() {
    return command();
  }

  public NodePackageName packageName() {
    return packageName;
  }
}
