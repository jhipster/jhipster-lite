package tech.jhipster.lite.generator.client.tools.playwright.domain;

import java.util.List;
import java.util.Map;

public class Playwright {

  private Playwright() {}

  private static final String PATH_INTEGRATION_TEST_PRIMARY_APP = "src/test/javascript/integration/common/primary/app";

  public static List<String> devDependencies() {
    return List.of("@playwright/test");
  }

  public static Map<String, String> playwrightScripts() {
    return Map.of("e2e", "npx playwright test --headed", "e2e:headless", "npx playwright test");
  }

  public static Map<String, String> playwrightPageObjectFiles() {
    return Map.ofEntries(Map.entry("Home-Page.ts", PATH_INTEGRATION_TEST_PRIMARY_APP));
  }

  public static Map<String, String> playwrightTestFiles() {
    return Map.ofEntries(Map.entry("Home.spec.ts", PATH_INTEGRATION_TEST_PRIMARY_APP));
  }
}
