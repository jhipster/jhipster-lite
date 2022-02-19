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
      "jest",
      "jest-sonar-reporter",
      "jest-css-modules",
      "typescript",
      "ts-jest",
      "ts-node",
      "vite"
    );
  }

  public static Map<String, String> scripts() {
    return Map.of("dev", "vite", "build", "tsc && vite build --emptyOutDir", "preview", "vite preview", "test", "jest");
  }

  public static List<String> files() {
    return List.of("tsconfig.json", "vite.config.ts", "jest.config.ts");
  }

  public static Map<String, String> reactFiles() {
    return Map.ofEntries(
      Map.entry("index.html", "src/main/webapp"),
      Map.entry("index.css", "src/main/webapp/app"),
      Map.entry("index.tsx", "src/main/webapp/app"),
      Map.entry("vite-env.d.ts", "src/main/webapp/app"),
      Map.entry("App.css", "src/main/webapp/app/common/primary/app"),
      Map.entry("App.tsx", "src/main/webapp/app/common/primary/app"),
      Map.entry("App.test.tsx", "src/test/javascript/spec/common/primary/app"),
      Map.entry("setupTests.ts", "src/main/webapp/config")
    );
  }
}
