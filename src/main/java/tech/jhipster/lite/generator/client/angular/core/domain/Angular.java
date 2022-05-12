package tech.jhipster.lite.generator.client.angular.core.domain;

import java.util.List;
import java.util.Map;

public class Angular {

  public static final String APP_MODULE = "app.module.ts";
  public static final String APP_ROUTING_MODULE = "app-routing.module.ts";
  public static final String APP_ROUTING_MODULE_SPEC = "app-routing.module.spec.ts";
  public static final String APP_COMPONENT = "app.component.ts";
  public static final String APP_COMPONENT_SPEC = "app.component.spec.ts";
  public static final String APP_COMPONENT_HTML = "app.component.html";
  public static final String APP_COMPONENT_CSS = "app.component.css";

  private Angular() {}

  public static List<String> devDependencies() {
    return List.of(
      "@angular-builders/jest",
      "@angular-devkit/build-angular",
      "@angular/cli",
      "@angular/compiler-cli",
      "@types/node",
      "@types/jest",
      "jest",
      "ts-jest",
      "jest-preset-angular",
      "jest-sonar-reporter",
      "typescript"
    );
  }

  public static List<String> dependencies() {
    return List.of(
      "@angular/animations",
      "@angular/cdk",
      "@angular/common",
      "@angular/compiler",
      "@angular/core",
      "@angular/material",
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
      "build", "ng build --output-path=target/classes/static",
      "watch", "ng build --watch --configuration development",
      "test", "ng test --coverage"
    );
    // @formatter:on
  }

  public static List<String> files() {
    return List.of("angular.json", "jest.conf.js", "tsconfig.app.json", "tsconfig.json", "tsconfig.spec.json");
  }

  public static Map<String, String> angularFiles() {
    String primaryApp = "app/common/primary/app";
    String environments = "environments";
    return Map.ofEntries(
      Map.entry("index.html", ""),
      Map.entry("main.ts", ""),
      Map.entry("polyfills.ts", ""),
      Map.entry("styles.css", ""),
      Map.entry(APP_MODULE, primaryApp),
      Map.entry(APP_COMPONENT, primaryApp),
      Map.entry(APP_COMPONENT_HTML, primaryApp),
      Map.entry(APP_COMPONENT_CSS, primaryApp),
      Map.entry(APP_ROUTING_MODULE, primaryApp),
      Map.entry(APP_ROUTING_MODULE_SPEC, primaryApp),
      Map.entry(APP_COMPONENT_SPEC, primaryApp),
      Map.entry("environment.prod.ts", environments),
      Map.entry("environment.prod.spec.ts", environments),
      Map.entry("environment.ts", environments),
      Map.entry("environment.spec.ts", environments)
    );
  }
}
