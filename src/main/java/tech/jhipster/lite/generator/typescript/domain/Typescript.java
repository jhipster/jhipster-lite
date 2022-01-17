package tech.jhipster.lite.generator.typescript.domain;

import java.util.List;
import java.util.Map;

public class Typescript {

  private Typescript() {}

  public static List<String> devDependencies() {
    return List.of(
      "@types/jest",
      "@typescript-eslint/eslint-plugin",
      "@typescript-eslint/parser",
      "eslint",
      "eslint-import-resolver-typescript",
      "eslint-plugin-import",
      "eslint-plugin-prettier",
      "jest",
      "ts-jest",
      "typescript"
    );
  }

  public static Map<String, String> scripts() {
    // @formatter:off
    return Map.of(
      "test", "jest",
      "test:watch", "jest --watch",
      "test:watch:all", "jest --watchAll",
      "eslint:ci", "eslint './**/*.{ts,js}'",
      "eslint", "eslint './**/*.{ts,js}' --fix"
    );
    // @formatter:on
  }

  public static List<String> files() {
    // @formatter:off
    return List.of(
      ".eslintrc.js",
      "jest.config.js",
      "tsconfig.json"
    );
    // @formatter:on
  }
}
