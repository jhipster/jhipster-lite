package tech.jhipster.lite.generator.client.react.cypress.domain;

import java.util.List;
import java.util.Map;

public class CypressReact {

  private CypressReact() {}

  public static List<String> devDependencies() {
    return List.of("@cypress/react", "@cypress/vite-dev-server", "cypress");
  }

  public static Map<String, String> cypressScripts() {
    return Map.of("cypress:open", "cypress open-ct", "cypress:run", "cypress run-ct");
  }

  public static Map<String, String> cypressFiles() {
    String pathRoot = "";
    String pathPlugins = "cypress/plugins";
    String pathTestConfig = "src/test/javascript/integration";

    return Map.ofEntries(
      Map.entry("cypress.json", pathRoot),
      Map.entry("index.ts", pathPlugins),
      Map.entry("tsconfig.json", pathTestConfig)
    );
  }

  public static Map<String, String> cypressTestFiles() {
    String pathIntegrationTestPrimaryApp = "src/test/javascript/integration/common/primary/app";

    return Map.ofEntries(Map.entry("App.test.tsx", pathIntegrationTestPrimaryApp));
  }
}
