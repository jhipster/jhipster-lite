package tech.jhipster.lite.generator.client.vue.router.domain;

import static tech.jhipster.lite.generator.typescript.common.domain.VitestShortcuts.vitestCoverageExclusion;
import static tech.jhipster.lite.module.domain.JHipsterModule.*;
import static tech.jhipster.lite.module.domain.nodejs.JHLiteNodePackagesVersionSource.VUE;

import java.util.function.Consumer;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class VueRouterModuleFactory {

  private static final JHipsterSource SOURCE = from("client/vue");
  private static final JHipsterSource APP_SOURCE = from("client/vue/webapp/app");

  private static final JHipsterDestination MAIN_DESTINATION = to("src/main/webapp/app");
  private static final JHipsterDestination TEST_DESTINATION = to("src/test/webapp");

  private static final String IMPORT_NEEDLE = "// jhipster-needle-main-ts-import";
  private static final String PROVIDER_NEEDLE = "// jhipster-needle-main-ts-provider";
  private static final String ROUTER_IMPORTS = "import router from './router';";
  private static final String ROUTER_PROVIDER = "app.use(router);";

  private static final String EXPORT_DEFAULT_APP_VUE = """
      export default {
        name: 'AppVue',
      };\
    """;

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    // @formatter:off
    return moduleBuilder(properties)
      .packageJson()
        .addDependency(packageName("vue-router"), VUE)
        .and()
      .files()
        .batch(APP_SOURCE, MAIN_DESTINATION)
          .addTemplate("router.ts")
        .and()
        .batch(APP_SOURCE.append("home"), MAIN_DESTINATION.append("home"))
          .addTemplate("application/HomeRouter.ts")
        .and()
        .add(SOURCE.template("webapp/app/router.ts"), MAIN_DESTINATION.append("router.ts"))
        .add(APP_SOURCE.template("test/webapp/unit/router/infrastructure/primary/HomeRouter.spec.ts.mustache"), TEST_DESTINATION.append("unit/router/infrastructure/primary/HomeRouter.spec.ts"))
        .and()
        .mandatoryReplacements()
          .in(path("src/main/webapp/app/main.ts"))
            .add(lineBeforeText(IMPORT_NEEDLE), ROUTER_IMPORTS)
            .add(lineBeforeText(PROVIDER_NEEDLE), ROUTER_PROVIDER)
            .and()
          .in(path("src/main/webapp/app/AppVue.vue"))
            .add(text("<HomepageVue />"), "<router-view />")
            .add(text("<script setup lang=\"ts\">"), "<script lang=\"ts\">")
            .add(text("import HomepageVue from './home/infrastructure/primary/HomepageVue.vue'"), EXPORT_DEFAULT_APP_VUE)
            .and()
          .and()
      .apply(patchVitestConfig())
      .build();
    // @formatter:on
  }

  private Consumer<JHipsterModuleBuilder> patchVitestConfig() {
    // @formatter:off
    return moduleBuilder -> moduleBuilder
      .apply(vitestCoverageExclusion("src/main/webapp/app/router.ts"));
    // @formatter:on
  }
}
