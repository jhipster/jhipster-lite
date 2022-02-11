package tech.jhipster.lite.generator.client.vite.vue.core.domain;

import java.util.List;

public class ViteVue {

  private ViteVue() {}

  public static List<String> dependencies() {
    return List.of("vue", "vue-class-component", "vue-router");
  }

  public static List<String> devDependencies() {
    return List.of(
      "@types/jest",
      "@vitejs/plugin-vue",
      "@vue/test-utils",
      "jest",
      "jest-transform-stub",
      "ts-jest",
      "typescript",
      "vite",
      "vue-jest",
      "vue-tsc"
    );
  }
}
