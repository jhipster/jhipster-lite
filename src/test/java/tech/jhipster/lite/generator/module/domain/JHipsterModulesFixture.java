package tech.jhipster.lite.generator.module.domain;

import static tech.jhipster.lite.generator.module.domain.JHipsterModule.*;

import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.module.domain.JHipsterModule.JHipsterModuleBuilder;

public final class JHipsterModulesFixture {

  private JHipsterModulesFixture() {}

  public static JHipsterModule module() {
    // @formatter:off
   return moduleForProject(new JHipsterProjectFolder(FileUtils.tmpDirForTest()))
    .context()
      .put("packageName", "com.test.myapp")
      .and()
    .files()
      .add(from("init/gitignore"), to(".gitignore"))
      .batch(from("server/javatool/base"), to("src/main/java/com/company/myapp/errors"))
        .add("Assert.java.mustache")
        .add("AssertionException.java.mustache")
        .and()
      .add(from("server/springboot/core/MainApp.java.mustache"), to("src/main/java/com/company/myapp/MyApp.java"))
      .and()
    .build();
    // @formatter:on
  }

  public static JHipsterModuleContext context() {
    return JHipsterModuleContext.builder(emptyModuleBuilder()).put("packageName", "com.test.myapp").build();
  }

  public static JHipsterModuleBuilder emptyModuleBuilder() {
    return moduleForProject(new JHipsterProjectFolder(FileUtils.tmpDirForTest()));
  }
}
