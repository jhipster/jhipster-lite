package tech.jhipster.lite.generator.client.vue.i18n.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;

@UnitTest
class VueI18nModuleFactoryTest {

  private static final VueI18nModuleFactory factory = new VueI18nModuleFactory();

  @Test
  void shouldBuildI18nModule() {
    JHipsterModule module = factory.buildModule(
      JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).projectBaseName("jhipster").build()
    );

    JHipsterModuleAsserter asserter = assertThatModuleWithFiles(
      module,
      packageJsonFile(),
      mainFile(),
      homepage(),
      homepageTest(),
      vitest()
    );
    asserter
      .hasFile("package.json")
      .containing(nodeDependency("i18next"))
      .containing(nodeDependency("i18next-vue"))
      .containing(nodeDependency("i18next-browser-languagedetector"))
      .and()
      .hasFile("src/main/webapp/app/i18n.ts")
      .containing(
        """
        import i18n from 'i18next';
        import LanguageDetector from 'i18next-browser-languagedetector';
        import { commonTranslations } from './common/CommonTranslations';
        import { toTranslationResources } from './Translations';

        i18n.use(LanguageDetector).init({
          fallbackLng: 'en',
          debug: false,
          interpolation: {
            escapeValue: false,
          },
          resources: toTranslationResources(commonTranslations),
        });

        export default i18n;
        """
      )
      .and()
      .hasFile("src/main/webapp/app/Translations.ts")
      .containing(
        """
        import type { Resource, ResourceKey, ResourceLanguage } from 'i18next';

        export type Translation = Record<string, unknown>;
        export type Translations = Record<string, Translation>;

        const toLanguage = ([key, value]: [string, ResourceKey]): [string, ResourceLanguage] => [
          key,
          {
            translation: value,
          },
        ];

        const mergeTranslations = (translations: Translations[]): Translations =>
          translations
            .flatMap(translations => Object.entries(translations))
            .reduce(
              (acc, [key, translation]) => ({
                ...acc,
                [key]: acc[key] ? { ...acc[key], ...translation } : translation,
              }),
              {} as Translations,
            );

        export const toTranslationResources = (...translations: Translations[]): Resource =>
          Object.entries(mergeTranslations(translations))
            .map(toLanguage)
            .reduce(
              (acc, current) => ({
                ...acc,
                [current[0]]: current[1],
              }),
              {},
            );
        """
      )
      .and()
      .hasFile("src/main/webapp/app/common/CommonTranslations.ts")
      .containing(
        """
        import type { Translations } from '@/Translations';
        import { en } from './locales/en';
        import { fr } from './locales/fr';

        export const commonTranslations: Translations = { fr, en };
        """
      )
      .and()
      .hasFile("src/main/webapp/app/main.ts")
      .containing("import i18next from './i18n';")
      .containing("import I18NextVue from 'i18next-vue';")
      .containing("app.use(I18NextVue, { i18next });")
      .and()
      .hasFile("src/main/webapp/app/common/primary/homepage/Homepage.html")
      .containing("<h2 v-html=\"$t('common.translationEnabled')\"></h2>")
      .and()
      .hasFile("src/test/setupTests.ts")
      .containing(
        """
        import { config } from '@vue/test-utils';

        config.global.mocks = {
          $t: msg => msg,
        };
        """
      )
      .and()
      .hasFile("vitest.config.ts")
      .containing("setupFiles: ['./src/test/setupTests.ts']")
      .and()
      .hasFile("src/main/webapp/app/common/locales/en.ts")
      .containing(
        """
        import type { Translation } from '@/Translations';

        export const en: Translation = {
          common: {
            translationEnabled: 'Internationalization enabled',
          },
        };
        """
      )
      .and()
      .hasFile("src/main/webapp/app/common/locales/fr.ts")
      .containing(
        """
        import type { Translation } from '@/Translations';

        export const fr: Translation = {
          common: {
            translationEnabled: 'Internationalisation activée',
          },
        };
        """
      )
      .and()
      .hasFile("src/test/webapp/unit/common/primary/homepage/Homepage.spec.ts")
      .containing("describe('App I18next', () => {");
  }

  private ModuleFile mainFile() {
    return file("src/test/resources/projects/vue/main.ts.template", "src/main/webapp/app/main.ts");
  }

  private ModuleFile homepage() {
    return file("src/test/resources/projects/vue/Homepage.html.template", "src/main/webapp/app/common/primary/homepage/Homepage.html");
  }

  private ModuleFile homepageTest() {
    return file(
      "src/test/resources/projects/vue/Homepage.spec.ts.template",
      "src/test/webapp/unit/common/primary/homepage/Homepage.spec.ts"
    );
  }

  private ModuleFile vitest() {
    return file("src/test/resources/projects/vue/vitest.config.ts.template", "./vitest.config.ts");
  }
}
