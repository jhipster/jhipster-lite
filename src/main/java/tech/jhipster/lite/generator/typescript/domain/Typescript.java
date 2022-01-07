package tech.jhipster.lite.generator.typescript.domain;

import java.util.List;
import java.util.Map;

public class Typescript {

  private Typescript() {}

  public static Map<String, String> dependencies() {
    // @formatter:off
    return Map.of(
      "@types/jest", "27.4.0",
      "@typescript-eslint/eslint-plugin", "5.9.0",
      "@typescript-eslint/parser", "5.9.0",
      "eslint", "8.6.0",
      "eslint-import-resolver-typescript", "2.5.0",
      "eslint-plugin-import", "2.25.4",
      "eslint-plugin-prettier", "4.0.0",
      "jest", "27.4.7",
      "ts-jest", "27.1.2",
      "typescript", "4.5.4"
    );
    // @formatter:on
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
