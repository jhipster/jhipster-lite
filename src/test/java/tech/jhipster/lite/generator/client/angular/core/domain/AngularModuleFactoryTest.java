package tech.jhipster.lite.generator.client.angular.core.domain;

import static org.mockito.Mockito.verify;
import static tech.jhipster.lite.module.domain.nodejs.NodePackageManager.PNPM;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.lintStagedConfigFileWithPrettier;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.nodeDependency;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.nodeScript;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.packageJsonFile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.nodejs.NodeLazyPackagesInstaller;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
@ExtendWith(MockitoExtension.class)
class AngularModuleFactoryTest {

  @InjectMocks
  private AngularModuleFactory factory;

  @Mock
  private NodeLazyPackagesInstaller nodeLazyPackagesInstaller;

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .projectBaseName("jhiTest")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, packageJsonFile(), lintStagedConfigFileWithPrettier())
      .hasFile("package.json")
      .containing(nodeDependency("zone.js"))
      .containing(nodeDependency("tslib"))
      .containing(nodeDependency("rxjs"))
      .containing(nodeDependency("@angular/router"))
      .containing(nodeDependency("@angular/platform-browser-dynamic"))
      .containing(nodeDependency("@angular/platform-browser"))
      .containing(nodeDependency("@angular/forms"))
      .containing(nodeDependency("@angular/material"))
      .containing(nodeDependency("@angular/core"))
      .containing(nodeDependency("@angular/compiler"))
      .containing(nodeDependency("@angular/common"))
      .containing(nodeDependency("@angular/cdk"))
      .containing(nodeDependency("@angular/animations"))
      .containing(nodeDependency("@angular/build"))
      .containing(nodeDependency("@typescript-eslint/eslint-plugin"))
      .containing(nodeDependency("@typescript-eslint/parser"))
      .containing(nodeDependency("angular-eslint"))
      .containing(nodeDependency("eslint"))
      .containing(nodeDependency("globals"))
      .containing(nodeDependency("typescript-eslint"))
      .containing(nodeDependency("npm-run-all2"))
      .containing(nodeScript("dev", "npm-run-all --parallel dev:*"))
      .containing(nodeScript("dev:ng", "ng serve"))
      .containing(nodeScript("ng", "ng"))
      .containing(nodeScript("watch", "npm-run-all --parallel watch:*"))
      .containing(nodeScript("watch:ng", "ng build --watch --configuration development"))
      .containing(nodeScript("start", "ng serve"))
      .containing(nodeScript("build", "npm-run-all build:*"))
      .containing(nodeScript("build:ng", "ng build"))
      .containing(nodeScript("test", "npm run watch:test"))
      .containing(nodeScript("watch:test", "ng test --watch"))
      .containing(nodeScript("test:coverage", "ng test --coverage"))
      .containing(nodeScript("lint", "eslint ."))
      .containing("  \"jestSonar\": {\n    \"reportPath\": \"target/test-results\",\n    \"reportFile\": \"TESTS-results-sonar.xml\"\n  }")
      .and()
      .hasFile(".gitignore")
      .containing(".angular/")
      .containing(".nx/")
      .and()
      .hasFile(".lintstagedrc.cjs")
      .containing(
        """
        module.exports = {
          '{src/**/,}*.ts': ['eslint --fix', 'prettier --write'],
          '*.{md,json,yml,html,css,scss,java,xml,feature}': ['prettier --write'],
        };
        """
      )
      .and()
      .hasFile("src/main/webapp/app/app.ts")
      .containing("this.appName.set('jhiTest')")
      .and()
      .hasPrefixedFiles(
        "",
        "jest.conf.mjs",
        "angular.json",
        "tsconfig.json",
        "tsconfig.app.json",
        "tsconfig.spec.json",
        "proxy.conf.json",
        "eslint.config.mjs"
      )
      .hasPrefixedFiles("src/main/webapp/app", "app.css", "app.ts", "app.html", "app.spec.ts", "app.route.spec.ts", "app.route.ts")
      .hasPrefixedFiles("src/main/webapp/content/images", "JHipster-Lite-neon-red.png", "AngularLogo.svg")
      .hasPrefixedFiles(
        "src/main/webapp/environments",
        "environment.ts",
        "environment.local.ts",
        "environment.local.spec.ts",
        "environment.spec.ts"
      )
      .hasPrefixedFiles("src/main/webapp", "index.html", "main.ts", "styles.css");
    verify(nodeLazyPackagesInstaller).runInstallIn(properties.projectFolder(), properties.nodePackageManager());
  }

  @Test
  void shouldBuildModuleWithPnpm() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .nodePackageManager(PNPM)
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, packageJsonFile(), lintStagedConfigFileWithPrettier())
      .hasFile("package.json")
      .containing(nodeScript("test", "pnpm run watch:test"));
  }

  @Test
  void shouldProxyBeUpdatedWhenServerPortPropertyNotDefault() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .projectBaseName("jhiTest")
      .put("serverPort", 8081)
      .build();

    JHipsterModule module = factory.buildModule(properties);
    assertThatModuleWithFiles(module, packageJsonFile(), lintStagedConfigFileWithPrettier())
      .hasFile("proxy.conf.json")
      .containing("\"target\": \"http://localhost:8081\"")
      .notContaining("\"target\": \"http://localhost:8080\"");
  }

  @Test
  void shouldProxyBeDefaultWhenServerPortPropertyMissing() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .projectBaseName("jhiTest")
      .build();

    JHipsterModule module = factory.buildModule(properties);
    assertThatModuleWithFiles(module, packageJsonFile(), lintStagedConfigFileWithPrettier())
      .hasFile("proxy.conf.json")
      .containing("\"target\": \"http://localhost:8080\"");
  }
}
