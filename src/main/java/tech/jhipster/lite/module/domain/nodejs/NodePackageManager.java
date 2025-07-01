package tech.jhipster.lite.module.domain.nodejs;

import java.util.stream.Stream;
import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public enum NodePackageManager {
  NPM("npm", "npm", new NodePackageName("npm"), "package-lock.json"),
  PNPM("pnpm", "pnpm", new NodePackageName("pnpm"), "pnpm-lock.yaml");

  private final String command;
  private final String propertyKey;
  private final NodePackageName packageName;
  private final String packageLockFile;

  NodePackageManager(String command, String propertyKey, NodePackageName packageName, String packageLockFile) {
    Assert.notNull("command", command);
    Assert.notNull("propertyKey", propertyKey);
    Assert.notNull("packageName", packageName);
    Assert.notNull("packageLockFile", packageLockFile);

    this.command = command;
    this.propertyKey = propertyKey;
    this.packageName = packageName;
    this.packageLockFile = packageLockFile;
  }

  public static NodePackageManager fromPropertyKey(String input) {
    return Stream.of(values())
      .filter(configFormat -> configFormat.propertyKey.equals(input))
      .findFirst()
      .orElseThrow();
  }

  public String packageLockFile() {
    return packageLockFile;
  }

  public String propertyKey() {
    return propertyKey;
  }

  public String command() {
    return command;
  }

  public String windowsCommand() {
    return "%s.cmd".formatted(command);
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
