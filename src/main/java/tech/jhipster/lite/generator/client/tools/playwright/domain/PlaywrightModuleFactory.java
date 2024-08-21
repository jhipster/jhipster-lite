package tech.jhipster.lite.generator.client.tools.playwright.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.packagejson.VersionSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class PlaywrightModuleFactory {

  private static final JHipsterSource SOURCE = from("client/common/playwright");

  private static final String WEBAPP_COMPONENT_TESTS = "src/test/webapp/component/";
  private static final String PLAYWRIGHT_TESTS = WEBAPP_COMPONENT_TESTS + "common/primary/app";

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .packageJson()
        .addDevDependency(packageName("@playwright/test"), VersionSource.COMMON)
        .addDevDependency(packageName("start-server-and-test"), VersionSource.COMMON)
        .addScript(scriptKey("test:component"), scriptCommand("start-server-and-test start http://localhost:9000 'playwright test --ui --config src/test/webapp/component/playwright.config.ts'"))
        .addScript(scriptKey("test:component:headless"), scriptCommand("start-server-and-test start http://localhost:9000 'playwright test --config src/test/webapp/component/playwright.config.ts'"))
        .and()
      .files()
        .add(SOURCE.template("playwright.config.ts"), to(WEBAPP_COMPONENT_TESTS + "playwright.config.ts"))
        .batch(SOURCE.append(PLAYWRIGHT_TESTS), to(PLAYWRIGHT_TESTS))
          .addFile("Home.spec.ts")
          .addFile("Home-Page.ts")
          .and()
        .and()
      .build();
    //@formatter:off
  }
}
