package tech.jhipster.lite.generator.client.vue.core.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;
import static tech.jhipster.lite.module.domain.packagejson.VersionSource.COMMON;
import static tech.jhipster.lite.module.domain.packagejson.VersionSource.VUE;
import static tech.jhipster.lite.module.domain.replacement.ReplacementCondition.notContainingReplacement;

import java.util.function.Consumer;
import tech.jhipster.lite.module.domain.Indentation;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.module.domain.replacement.TextReplacer;
import tech.jhipster.lite.shared.error.domain.Assert;

public class VueModulesFactory {

  private static final JHipsterSource SOURCE = from("client/vue");
  private static final JHipsterSource APP_SOURCE = from("client/vue/webapp/app");
  private static final JHipsterSource DOCUMENTATION_SOURCE = SOURCE.append("documentation");
  private static final JHipsterSource IMAGE_SOURCE = SOURCE.append("webapp/content/images");

  private static final JHipsterDestination MAIN_DESTINATION = to("src/main/webapp/app");
  private static final JHipsterDestination TEST_DESTINATION = to("src/test/webapp");

  private static final String IMPORT_NEEDLE = "// jhipster-needle-main-ts-import";
  private static final String PROVIDER_NEEDLE = "// jhipster-needle-main-ts-provider";
  private static final String PINIA_IMPORTS =
    """
    import { createPinia } from 'pinia';
    import piniaPersist from 'pinia-plugin-persistedstate';
    """;
  private static final String PINIA_PROVIDER =
    """
    const pinia = createPinia();
    pinia.use(piniaPersist);
    app.use(pinia);
    """;

  public JHipsterModule buildVueModule(JHipsterModuleProperties properties) {
    //@formatter:off
    return moduleBuilder(properties)
      .preCommitActions(stagedFilesFilter("{src/**/,}*.{ts,vue}"), preCommitCommands("eslint --fix", "prettier --write"))
      .documentation(documentationTitle("Vue"), DOCUMENTATION_SOURCE.file("vue.md"))
      .packageJson()
        .removeDevDependency(packageName("@tsconfig/recommended"))
        .addDependency(packageName("vue"), VUE)
        .addDependency(packageName("axios"), VUE)
        .addDependency(packageName("vue-router"), VUE)
        .addDevDependency(packageName("@vitejs/plugin-vue"), VUE)
        .addDevDependency(packageName("@vue/test-utils"), VUE)
        .addDevDependency(packageName("@vue/tsconfig"), VUE)
        .addDevDependency(packageName("eslint-plugin-vue"), VUE)
        .addDevDependency(packageName("jsdom"), COMMON)
        .addDevDependency(packageName("vite"), COMMON)
        .addDevDependency(packageName("vue-tsc"), VUE)
        .addDevDependency(packageName("@types/sinon"), VUE)
        .addDevDependency(packageName("sinon"), VUE)
        .addDevDependency(packageName("piqure"), VUE)
        .addScript(scriptKey("build"), scriptCommand("npm-run-all build:*"))
        .addScript(scriptKey("build:tsc"), scriptCommand("vue-tsc -p tsconfig.build.json --noEmit"))
        .addScript(scriptKey("build:vite"), scriptCommand("vite build --emptyOutDir"))
        .addScript(scriptKey("dev"), scriptCommand("npm-run-all --parallel dev:*"))
        .addScript(scriptKey("dev:vite"), scriptCommand("vite"))
        .addScript(scriptKey("preview"), scriptCommand("vite preview"))
        .addScript(scriptKey("start"), scriptCommand("vite"))
        .addScript(scriptKey("watch:tsc"), scriptCommand("npm run build:tsc -- --watch"))
        .and()
      .files()
        .add(SOURCE.template("eslint.config.js.mustache"), to("eslint.config.js"))
        .add(SOURCE.file("tsconfig.build.json"), to("tsconfig.build.json"))
        .batch(SOURCE, to("."))
          .addTemplate("vite.config.ts")
          .addTemplate("vitest.config.ts")
          .and()
        .add(SOURCE.template("webapp/index.html"), to("src/main/webapp/index.html"))
        .batch(APP_SOURCE, MAIN_DESTINATION)
          .addTemplate("env.d.ts")
          .addTemplate("main.ts")
          .addTemplate("AppVue.vue")
          .addTemplate("injections.ts")
          .addTemplate("router.ts")
          .and()
        .batch(APP_SOURCE.append("home"), MAIN_DESTINATION.append("home"))
          .addTemplate("application/HomeRouter.ts")
          .addTemplate("infrastructure/primary/HomepageVue.vue")
          .and()
        .add(APP_SOURCE.template("shared/http/infrastructure/secondary/AxiosHttp.ts.mustache"), MAIN_DESTINATION.append("shared/http/infrastructure/secondary/AxiosHttp.ts"))
        .batch(IMAGE_SOURCE, to("src/main/webapp/content/images"))
          .addFile("JHipster-Lite-neon-green.png")
          .addFile("VueLogo.png")
          .and()
        .add(SOURCE.template("webapp/app/router.ts"), MAIN_DESTINATION.append("router.ts"))
        .add(SOURCE.template("Dummy.spec.ts"), to("src/test/webapp/unit/Dummy.spec.ts"))
        .add(APP_SOURCE.template("test/webapp/unit/shared/http/infrastructure/secondary/AxiosHttp.spec.ts.mustache"), TEST_DESTINATION.append("unit/shared/http/infrastructure/secondary/AxiosHttp.spec.ts"))
        .add(APP_SOURCE.template("test/webapp/unit/shared/http/infrastructure/secondary/AxiosStub.ts.mustache"), TEST_DESTINATION.append("unit/shared/http/infrastructure/secondary/AxiosStub.ts"))
        .add(APP_SOURCE.template("test/webapp/unit/router/infrastructure/primary/HomeRouter.spec.ts.mustache"), TEST_DESTINATION.append("unit/router/infrastructure/primary/HomeRouter.spec.ts"))
        .and()
      .apply(patchTsConfig(properties))
      .build();
    //@formatter:on
  }

  private Consumer<JHipsterModuleBuilder> patchTsConfig(JHipsterModuleProperties properties) {
    //@formatter:off
    return moduleBuilder -> moduleBuilder
      .mandatoryReplacements()
        .in(path("tsconfig.json"))
          .add(text("@tsconfig/recommended/tsconfig.json"), "@vue/tsconfig/tsconfig.dom.json")
          .add(lineAfterRegex("\"compilerOptions\":"), compilerOption("sourceMap", true, properties.indentation()))
          .add(lineAfterRegex("\"compilerOptions\":"), compilerOption("allowJs", true, properties.indentation()))
          .add(new TextReplacer(notContainingReplacement(), "\"types\": ["), "\"types\": [\"vite/client\", ")
          .and()
        .and();
    //@formatter:on
  }

  private static String compilerOption(String optionName, boolean optionValue, Indentation indentation) {
    return indentation.times(2) + "\"%s\": %s,".formatted(optionName, optionValue);
  }

  public JHipsterModule buildPiniaModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .packageJson()
        .addDependency(packageName("pinia"), VUE)
        .addDependency(packageName("pinia-plugin-persistedstate"), VUE)
        .addDevDependency(packageName("@pinia/testing"), VUE)
        .and()
      .mandatoryReplacements()
        .in(path("src/main/webapp/app/main.ts"))
          .add(lineBeforeText(IMPORT_NEEDLE), PINIA_IMPORTS)
          .add(lineBeforeText(PROVIDER_NEEDLE), PINIA_PROVIDER)
          .and()
        .and()
      .build();
    //@formatter:on
  }
}
