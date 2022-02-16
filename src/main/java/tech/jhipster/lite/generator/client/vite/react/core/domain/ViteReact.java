package tech.jhipster.lite.generator.client.vite.react.core.domain;

import java.util.List;
import java.util.Map;

public class ViteReact {

  private ViteReact() {}

  public static List<String> dependencies() {
    return List.of("react", "react-dom");
  }

  public static List<String> devDependencies() {
    return List.of(
      "@testing-library/jest-dom",
      "@testing-library/react",
      "@testing-library/user-event",
      "@types/jest",
      "@types/node",
      "@types/react",
      "@types/react-dom",
      "@vitejs/plugin-react",
      "typescript",
      "vite",
      "webpack",
      "webpack-cli",
      "webpack-dev-server"
    );
  }

  public static Map<String, String> scripts() {
    return Map.of("dev", "vite", "build", "tsc && vite build", "preview", "vite preview");
  }

  public static List<String> files() {
    return List.of("tsconfig.json", "tsconfig.node.json", "vite.config.ts");
  }

  public static Map<String, String> reactFiles() {
    return Map.ofEntries(
      Map.entry("index.html", "public"),
      Map.entry("index.css", "src/main/webapp"),
      Map.entry("index.tsx", "src/main/webapp"),
      Map.entry("react-app-env.d.ts", "src/main/webapp"),
      Map.entry("App.css", "src/main/webapp/app/common/primary/app"),
      Map.entry("App.tsx", "src/main/webapp/app/common/primary/app"),
      Map.entry("App.test.tsx", "src/test/javascript/spec/common/primary/app"),
      Map.entry("setupTests.ts", "src")
    );
  }
}
