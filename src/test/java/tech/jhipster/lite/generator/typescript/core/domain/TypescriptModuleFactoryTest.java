package tech.jhipster.lite.generator.typescript.core.domain;

import static org.mockito.Mockito.verify;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
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
class TypescriptModuleFactoryTest {

  @InjectMocks
  private TypescriptModuleFactory factory;

  @Mock
  private NodeLazyPackagesInstaller nodeLazyPackagesInstaller;

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, packageJsonFile())
      .hasFile("package.json")
      .containing("\"type\": \"module\"")
      .containing(nodeDependency("typescript"))
      .containing(nodeDependency("@tsconfig/recommended"))
      .containing(nodeDependency("@typescript-eslint/eslint-plugin"))
      .containing(nodeDependency("@typescript-eslint/parser"))
      .containing(nodeDependency("@vitest/coverage-istanbul"))
      .containing(nodeDependency("eslint"))
      .containing(nodeDependency("eslint-config-prettier"))
      .containing(nodeDependency("@eslint/js"))
      .containing(nodeDependency("globals"))
      .containing(nodeDependency("typescript-eslint"))
      .containing(nodeDependency("vite-tsconfig-paths"))
      .containing(nodeDependency("vitest"))
      .containing(nodeDependency("vitest-sonar-reporter"))
      .containing(nodeScript("test", "npm run watch:test"))
      .containing(nodeScript("test:coverage", "vitest run --coverage"))
      .containing(nodeScript("watch", "npm-run-all --parallel watch:*"))
      .containing(nodeScript("watch:test", "vitest --"))
      .containing(nodeScript("watch:tsc", "tsc --noEmit --watch"))
      .containing(nodeScript("lint", "eslint ."))
      .and()
      .hasPrefixedFiles("", "eslint.config.js", "tsconfig.json");
    verify(nodeLazyPackagesInstaller).runInstallIn(properties.projectFolder());
  }
}
