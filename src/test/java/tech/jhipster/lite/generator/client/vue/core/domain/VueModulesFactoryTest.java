package tech.jhipster.lite.generator.client.vue.core.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class VueModulesFactoryTest {

  private static final VueModulesFactory factory = new VueModulesFactory();

  @Test
  void shouldCreateVueModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .projectBaseName("jhiTest")
      .basePackage("tech.jhipster.jhlitest")
      .build();

    JHipsterModule module = factory.buildVueModule(properties);

    //@formatter:off
    assertThatModuleWithFiles(module, packageJsonFile(), lintStagedConfigFile(), tsConfigFile(), vitestConfigFile(), eslintConfigFile())
      .hasFiles("documentation/vue.md")
      .hasFile("package.json")
        .notContaining(nodeDependency("@tsconfig/recommended"))
        .containing(nodeDependency("vue"))
        .containing(nodeDependency("@vitejs/plugin-vue"))
        .containing(nodeDependency("@vue/test-utils"))
        .containing(nodeDependency("@vue/tsconfig"))
        .containing(nodeDependency("eslint-plugin-vue"))
        .containing(nodeDependency("jsdom"))
        .containing(nodeDependency("vite"))
        .containing(nodeDependency("vue-tsc"))
        .containing(nodeDependency("@types/sinon"))
        .containing(nodeDependency("sinon"))
        .containing(nodeDependency("axios"))
        .containing(nodeDependency("vue-router"))
        .containing(nodeDependency("piqure"))
        .containing(nodeScript("build", "npm-run-all build:*"))
        .containing(nodeScript("build:tsc", "vue-tsc -p tsconfig.build.json --noEmit"))
        .containing(nodeScript("build:vite", "vite build --emptyOutDir"))
        .containing(nodeScript("dev", "npm-run-all --parallel dev:*"))
        .containing(nodeScript("dev:vite", "vite"))
        .containing(nodeScript("watch:tsc", "npm run build:tsc -- --watch"))
        .containing(nodeScript("preview", "vite preview"))
        .containing(nodeScript("start", "vite"))
        .and()
      .hasFile(".lintstagedrc.cjs")
        .containing(
          """
            module.exports = {
              '{src/**/,}*.{ts,vue}': ['eslint --fix', 'prettier --write'],
              '*.{md,json,yml,html,css,scss,java,xml,feature}': ['prettier --write'],
            };
            """
        )
      .and()
      .hasPrefixedFiles("", "eslint.config.js", "tsconfig.build.json", "vite.config.ts", "vitest.config.ts")
      .hasFile("tsconfig.json")
        .containing("\"extends\": \"@vue/tsconfig/tsconfig.dom.json\"")
        .containing("\"allowJs\": true,")
        .containing("\"sourceMap\": true,")
        .containing("\"types\": [\"vite/client\", ")
        .and()
      .hasFile("vitest.config.ts")
        .containing("import vue from '@vitejs/plugin-vue';")
        .containing("plugins: [vue(), tsconfigPaths()],")
        .containing("environment: 'jsdom',")
        .containing("""
                exclude: [
                  ...configDefaults.coverage.exclude as string[],
                  'src/main/webapp/app/main.ts',
                  'src/main/webapp/app/injections.ts',
                  'src/main/webapp/app/router.ts',
                  'src/main/webapp/**/*.component.ts',
          """
        )
        .and()
      .hasFile("eslint.config.js")
        .containing("import vue from 'eslint-plugin-vue';")
        .containing("""
          ...vue.configs['flat/recommended'],
          {
            files: ['**/*.vue'],
            languageOptions: {
              parserOptions: { parser: '@typescript-eslint/parser' },
              globals: { ...globals.browser },
            },
          },
        """
        )
      .containing("""
              rules: {
                quotes: ['error', 'single', { avoidEscape: true }],
                '@typescript-eslint/no-empty-object-type': 'off',
                '@typescript-eslint/no-explicit-any': 'off',
                'vue/html-self-closing': 'off',
          """
        )
      .and()
      .hasFiles("src/main/webapp/app/shared/http/infrastructure/secondary/AxiosHttp.ts")
      .hasFiles("src/main/webapp/index.html")
      .hasPrefixedFiles("src/main/webapp/app", "env.d.ts", "AppVue.vue", "injections.ts", "router.ts", "main.ts")
      .hasPrefixedFiles("src/main/webapp/app/home","infrastructure/primary/HomepageVue.vue", "application/HomeRouter.ts")
      .hasPrefixedFiles("src/main/webapp/content/images", "JHipster-Lite-neon-green.png", "VueLogo.png")
      .hasFiles("src/test/webapp/unit/Dummy.spec.ts")
      .hasFiles("src/test/webapp/unit/shared/http/infrastructure/secondary/AxiosHttp.spec.ts")
      .hasFiles("src/test/webapp/unit/shared/http/infrastructure/secondary/AxiosStub.ts")
      .hasFiles("src/test/webapp/unit/router/infrastructure/primary/HomeRouter.spec.ts");
    //@formatter:on
  }

  @Test
  void shouldCreatePiniaModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("tech.jhipster.jhlitest")
      .build();

    JHipsterModule module = factory.buildPiniaModule(properties);

    assertThatModuleWithFiles(
      module,
      packageJsonFile(),
      new ModuleFile("src/test/resources/projects/vue/main.ts.template", "src/main/webapp/app/main.ts")
    )
      .hasFile("package.json")
      .containing(nodeDependency("pinia"))
      .containing(nodeDependency("pinia-plugin-persistedstate"))
      .containing(nodeDependency("@pinia/testing"))
      .and()
      .hasFile("src/main/webapp/app/main.ts")
      .containing("import { createPinia } from 'pinia';")
      .containing("import piniaPersist from 'pinia-plugin-persistedstate';")
      .containing(
        """
        const pinia = createPinia();
        pinia.use(piniaPersist);
        app.use(pinia);
        """
      );
  }
}
