package tech.jhipster.lite.generator.client.vue.pinia.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;
import static tech.jhipster.lite.module.domain.nodejs.JHLiteNodePackagesVersionSource.VUE;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class VuePiniaModuleFactory {

  private static final String IMPORT_NEEDLE = "// jhipster-needle-main-ts-import";
  private static final String PROVIDER_NEEDLE = "// jhipster-needle-main-ts-provider";
  private static final String PINIA_IMPORTS = """
    import { createPinia } from 'pinia';
    import piniaPersist from 'pinia-plugin-persistedstate';
    """;
  private static final String PINIA_PROVIDER = """
    const pinia = createPinia();
    pinia.use(piniaPersist);
    app.use(pinia);
    """;

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    // @formatter:off
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
    // @formatter:on
  }
}
