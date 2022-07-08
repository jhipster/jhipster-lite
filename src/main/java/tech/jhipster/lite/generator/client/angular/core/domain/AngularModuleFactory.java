package tech.jhipster.lite.generator.client.angular.core.domain;

import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.module.domain.JHipsterModule.*;
import static tech.jhipster.lite.module.domain.packagejson.VersionSource.ANGULAR;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.Indentation;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class AngularModuleFactory {

  private static final JHipsterSource SOURCE = from("client/angular/core");

  private static final String CACHE_NEEDLE = "  \"cacheDirectories\":";
  private static final String BREAK = "\n";

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .packageJson()
        .addDependency(packageName("@angular/animations"), ANGULAR)
        .addDependency(packageName("@angular/cdk"), ANGULAR)
        .addDependency(packageName("@angular/common"), ANGULAR)
        .addDependency(packageName("@angular/compiler"), ANGULAR)
        .addDependency(packageName("@angular/core"), ANGULAR)
        .addDependency(packageName("@angular/material"), ANGULAR)
        .addDependency(packageName("@angular/forms"), ANGULAR)
        .addDependency(packageName("@angular/platform-browser"), ANGULAR)
        .addDependency(packageName("@angular/platform-browser-dynamic"), ANGULAR)
        .addDependency(packageName("@angular/router"), ANGULAR)
        .addDependency(packageName("rxjs"), ANGULAR)
        .addDependency(packageName("tslib"), ANGULAR)
        .addDependency(packageName("zone.js"), ANGULAR)
        .addDevDependency(packageName("@angular-builders/jest"), ANGULAR)
        .addDevDependency(packageName("@angular-devkit/build-angular"), ANGULAR)
        .addDevDependency(packageName("@angular/cli"), ANGULAR)
        .addDevDependency(packageName("@angular/compiler-cli"), ANGULAR)
        .addDevDependency(packageName("@types/node"), ANGULAR)
        .addDevDependency(packageName("@types/jest"), ANGULAR)
        .addDevDependency(packageName("jest"), ANGULAR)
        .addDevDependency(packageName("jest-environment-jsdom"), ANGULAR)
        .addDevDependency(packageName("ts-jest"), ANGULAR)
        .addDevDependency(packageName("jest-preset-angular"), ANGULAR)
        .addDevDependency(packageName("jest-sonar-reporter"), ANGULAR)
        .addDevDependency(packageName("typescript"), ANGULAR)
        .addScript(scriptKey("ng"), scriptCommand("ng"))
        .addScript(scriptKey("start"), scriptCommand("ng serve"))
        .addScript(scriptKey("build"), scriptCommand("ng build --output-path=target/classes/static"))
        .addScript(scriptKey("watch"), scriptCommand("ng build --watch --configuration development"))
        .addScript(scriptKey("test"), scriptCommand("ng test --coverage"))
        .and()
      .optionalReplacements()
        .in("package.json")
          // TODO put this in common
          .add(justLineBefore(text(CACHE_NEEDLE)), jestSonar(properties.indentation()))
          .and()
        .and()
      .files()
        .add(SOURCE.file("jest.conf.js"), to("jest.conf.js"))
        .add(SOURCE.file("angular.json"), to("angular.json"))
        .add(SOURCE.file("tsconfig.json"), to("tsconfig.json"))
        .add(SOURCE.file("tsconfig.app.json"), to("tsconfig.app.json"))
        .add(SOURCE.file("tsconfig.spec.json"), to("tsconfig.spec.json"))
        .add(SOURCE.file("proxy.conf.json"), to("proxy.conf.json"))
        .batch(SOURCE.file("src/main/webapp/app"), to("src/main/webapp/app"))
          .template("app.component.ts")
          .template("app.component.css")
          .template("app.component.html")
          .template("app.component.spec.ts")
          .template("app.module.ts")
          .template("app-routing.module.ts")
          .template("app-routing.module.spec.ts")
          .and()
        .batch(SOURCE.file("src/main/webapp/content/images"), to("src/main/webapp/content/images"))
          .file("JHipster-Lite-neon-red.png")
          .file("AngularLogo.svg")
          .and()
        .batch(SOURCE.file("src/main/webapp/environments"), to("src/main/webapp/environments"))
          .template("environment.prod.ts")
          .template("environment.prod.spec.ts")
          .template("environment.ts")
          .template("environment.spec.ts")
          .and()
        .batch(SOURCE.file("src/main/webapp"), to("src/main/webapp"))
          .template("index.html")
          .template("styles.css")
          .template("main.ts")
          .template("polyfills.ts")
          .and()
        .and()
      .build();
    //@formatter:on
  }

  // TODO check duplicate worth ?
  private String jestSonar(Indentation indentation) {
    return new StringBuilder()
      .append(indentation.spaces())
      .append("\"jestSonar\": {")
      .append(BREAK)
      .append(indentation.times(2))
      .append("\"reportPath\": \"target/test-results/jest\",")
      .append(BREAK)
      .append(indentation.times(2))
      .append("\"reportFile\": \"TESTS-results-sonar.xml\"")
      .append(BREAK)
      .append(indentation.spaces())
      .append("},")
      .toString();
  }
}
