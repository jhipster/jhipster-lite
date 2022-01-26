package tech.jhipster.lite.generator.client.angular.core.domain;

import java.util.List;
import java.util.Map;

public class Angular {

  private Angular() {}

  public static List<String> devDependencies() {
    return List.of(
      "@angular-builders/jest",
      "@angular-devkit/build-angular",
      "@angular/cli",
      "@angular/compiler-cli",
      "@types/jasmine",
      "@types/node",
      "@types/jest",
      "jest",
      "ts-jest",
      "jest-preset-angular",
      "jasmine-core",
      "typescript"
    );
  }

  public static List<String> dependencies() {
    return List.of(
      "@angular/animations",
      "@angular/common",
      "@angular/compiler",
      "@angular/core",
      "@angular/forms",
      "@angular/platform-browser",
      "@angular/platform-browser-dynamic",
      "@angular/router",
      "rxjs",
      "tslib",
      "zone.js"
    );
  }

  public static Map<String, String> scripts() {
    // @formatter:off
    return Map.of(
      "ng", "ng",
      "start", "ng serve",
      "build", "ng build",
      "watch", "ng build --watch --configuration development",
      "test", "ng test"
    );
    // @formatter:on
  }

  public static List<String> files() {
    // @formatter:offProjectLocalRepository
    return List.of("angular.json", "jest.conf.js", "tsconfig.app.json", "tsconfig.json", "tsconfig.spec.json");
    // @formatter:on
  }

  public static Map<String, String> angularFiles() {
    // @formatter:offProjectLocalRepository
    return Map.of(
      "index.html",
      "",
      "main.ts",
      "",
      "polyfills.ts",
      "",
      "styles.css",
      "",
      "app.module.ts",
      "app",
      "app.component.ts",
      "app",
      "app.component.css",
      "app",
      "app.component.html",
      "app",
      "app-routing.module.ts",
      "app",
      "app.component.spec.ts",
      "app"
    );
    // @formatter:on
  }
}
