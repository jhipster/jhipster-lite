package tech.jhipster.lite.generator.client.react.i18n.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;

@UnitTest
class ReactI18nModuleFactoryTest {

  private static final String APP_TSX = "src/main/webapp/app/common/primary/app/App.tsx";

  private final ReactI18nModuleFactory factory = new ReactI18nModuleFactory();

  @Test
  void shouldBuildI18nModule() {
    JHipsterModule module = factory.buildModule(
      JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).projectBaseName("jhipster").build()
    );

    JHipsterModuleAsserter asserter = assertThatModuleWithFiles(module, packageJsonFile(), app(), appTest(), index());

    asserter
      .hasFile("package.json")
      .containing(nodeDependency("i18next"))
      .containing(nodeDependency("i18next-browser-languagedetector"))
      .containing(nodeDependency("i18next-http-backend"))
      .containing(nodeDependency("react-i18next"))
      .and()
      .hasFiles("src/main/webapp/app/i18n.ts")
      .hasFile("src/main/webapp/app/index.tsx")
      .containing("import './i18n'")
      .and()
      .hasFile("src/main/webapp/app/common/primary/app/App.tsx")
      .containing("import { useTranslation } from 'react-i18next")
      .containing("const { t } = useTranslation();")
      .containing("{t('translationEnabled')}")
      .and()
      .hasPrefixedFiles("src/main/webapp/assets/locales/", "en/translation.json", "fr/translation.json")
      .hasFile("src/test/webapp/unit/common/primary/app/App.spec.tsx")
      .containing("describe('App I18next', () => {");
  }

  private ModuleFile app() {
    return file("src/test/resources/projects/react-app/App.tsx", APP_TSX);
  }

  private ModuleFile appTest() {
    return file("src/test/resources/projects/react-app/App.spec.tsx", "src/test/webapp/unit/common/primary/app/App.spec.tsx");
  }

  private ModuleFile index() {
    return file("src/test/resources/projects/react-app/index.tsx", "src/main/webapp/app/index.tsx");
  }
}
