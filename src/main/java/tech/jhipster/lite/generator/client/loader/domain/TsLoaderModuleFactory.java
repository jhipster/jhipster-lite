package tech.jhipster.lite.generator.client.loader.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class TsLoaderModuleFactory {

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    JHipsterSource source = from("client/loader");

    String destination = "shared/loader/infrastructure/primary";

    //@formatter:off
    return moduleBuilder(properties)
      .files()
        .add(source.template("Loader.ts"), to("src/main/webapp/app").append(destination).append("Loader.ts"))
        .add(source.template("Loader.spec.ts"), to("src/test/webapp/unit").append(destination).append("Loader.spec.ts"))
        .and()
      .build();
    //@formatter:on
  }
}
