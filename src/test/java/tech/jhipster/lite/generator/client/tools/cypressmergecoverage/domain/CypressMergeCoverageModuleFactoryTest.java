package tech.jhipster.lite.generator.client.tools.cypressmergecoverage.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.cypressTestConfigFile;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.eslintConfigFile;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.nodeDependency;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.nodeScript;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.packageJsonFile;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.pomFile;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.tsConfigFile;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.viteConfigFile;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.vitestConfigFile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
@ExtendWith(MockitoExtension.class)
class CypressMergeCoverageModuleFactoryTest {

  @InjectMocks
  private CypressMergeCoverageModuleFactory factory;

  @Test
  void shouldBuildCypressMergeCoverageModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .projectBaseName("jhiTest")
      .basePackage("tech.jhipster.jhlitest")
      .build();

    JHipsterModule module = factory.buildCypressMergeCoverage(properties);

    assertThatModuleWithFiles(
      module,
      packageJsonFile(),
      tsConfigFile(),
      vitestConfigFile(),
      viteConfigFile(),
      eslintConfigFile(),
      pomFile(),
      cypressTestConfigFile()
    )
      .hasFile("package.json")
      .containing(nodeDependency("@cypress/code-coverage"))
      .containing(nodeDependency("cpy-cli"))
      .containing(nodeDependency("rimraf"))
      .containing(nodeDependency("vite-plugin-istanbul"))
      .containing(nodeDependency("nyc"))
      .containing(nodeScript("test:coverage:check"))
      .containing(nodeScript("test:coverage:clean"))
      .containing(nodeScript("test:coverage:copy:unit"))
      .containing(nodeScript("test:coverage:copy:component"))
      .containing(nodeScript("test:coverage:merge"))
      .containing(nodeScript("test:coverage:report"))
      .and()
      .hasFile("vite.config.ts")
      .matchingSavedSnapshot()
      .and()
      .hasFile("vitest.config.ts")
      .matchingSavedSnapshot()
      .and()
      .hasFile(".nycrc.json")
      .and()
      .hasFile("src/test/webapp/component/cypress-config.ts")
      .matchingSavedSnapshot()
      .and()
      .hasFile("src/test/webapp/component/.nycrc.json")
      .and()
      .hasFile("src/test/webapp/component/support/component-tests.ts");
  }
}
