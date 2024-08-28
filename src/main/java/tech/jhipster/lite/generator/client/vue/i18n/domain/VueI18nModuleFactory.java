package tech.jhipster.lite.generator.client.vue.i18n.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.packagejson.VersionSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class VueI18nModuleFactory {

  private static final JHipsterSource APP_SOURCE = from("client/common/i18n");
  private static final JHipsterSource COMMON_CONTEXT_SOURCE = from("client/common/i18n/app");
  private static final JHipsterSource ASSETS_SOURCE = from("client/common/i18n/app/locales");
  private static final JHipsterSource TEST_SOURCE = from("client/vue/i18n/src/test");

  private static final String INDEX = "src/main/webapp/app/";
  private static final String INDEX_TEST = "src/test/";

  private static final String PROVIDER_NEEDLE = "// jhipster-needle-main-ts-provider";

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .packageJson()
      .addDependency(packageName("i18next"), VersionSource.COMMON)
      .addDependency(packageName("i18next-vue"), VersionSource.VUE)
      .addDependency(packageName("i18next-browser-languagedetector"), VersionSource.COMMON)
      .and()
      .files()
      .batch(APP_SOURCE, to(INDEX))
        .addFile("i18n.ts")
        .addFile("Translations.ts")
        .and()
      .batch(COMMON_CONTEXT_SOURCE, to(INDEX + "common/"))
        .addFile("CommonTranslations.ts")
        .and()
      .batch(ASSETS_SOURCE, to(INDEX + "common/locales/"))
        .addFile("en.ts")
        .addFile("fr.ts")
      .and()
      .batch(TEST_SOURCE, to(INDEX_TEST))
        .addFile("setupTests.ts")
        .and()
      .and()
      .mandatoryReplacements()
        .in(path(INDEX + "main.ts"))
          .add(lineBeforeText("// jhipster-needle-main-ts-import"), "import i18next from './i18n';")
          .add(lineBeforeText("// jhipster-needle-main-ts-import"), "import I18NextVue from 'i18next-vue';")
          .add(lineBeforeText(PROVIDER_NEEDLE
          ), "app.use(I18NextVue, { i18next });")
        .and()
        .in(path(INDEX + "/common/primary/homepage/Homepage.html"))
          .add(lineAfterRegex("Vue 3 \\+ TypeScript \\+ Vite"), properties.indentation().times(1) + "<h2 v-html=\"$t('common.translationEnabled')\"></h2>")
        .and()
        .in(path("./vitest.config.ts"))
          .add(lineAfterRegex("test:"), properties.indentation().times(2) + "setupFiles: ['./src/test/setupTests.ts'],")
          .and()
        .in(path(INDEX_TEST + "webapp/unit/common/primary/homepage/Homepage.spec.ts"))
          .add(append(), LINE_BREAK + """
          describe('App I18next', () => {
            it('should renders with translation', () => {
              wrap();

              expect(wrapper.text()).toContain("translationEnabled");
            });
          });
          """)
        .and()
      .and()
      .build();
    //@formatter:off
  }
}
