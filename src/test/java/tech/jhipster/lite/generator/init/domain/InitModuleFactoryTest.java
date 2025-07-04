package tech.jhipster.lite.generator.init.domain;

import static org.mockito.Mockito.when;
import static tech.jhipster.lite.module.domain.nodejs.NodePackageManager.NPM;
import static tech.jhipster.lite.module.domain.nodejs.NodePackageManager.PNPM;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModule;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.nodeDependency;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.nodeScript;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.nodejs.NodePackageManager;
import tech.jhipster.lite.module.domain.nodejs.NodePackageVersion;
import tech.jhipster.lite.module.domain.nodejs.NodeVersions;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
@ExtendWith(MockitoExtension.class)
class InitModuleFactoryTest {

  @Mock
  private NodeVersions nodeVersions;

  @InjectMocks
  private InitModuleFactory factory;

  @Test
  void shouldBuildModule() {
    mockNodeVersion();
    mockNodePackageManagerVersion(NPM, "11.9.9");
    JHipsterModuleProperties properties = defaultProperties(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModule(module)
      .hasFile("README.md")
      .containing("# Test Project")
      .and()
      .hasFiles(".gitignore", ".gitattributes")
      .hasFile(".editorconfig")
      .containing("end_of_line = crlf")
      .containing("indent_size = 4")
      .and()
      .hasFile("package.json")
      .containing("test-project")
      .containing("Test Project")
      .containing("\"node\": \">=16\"")
      .containing(nodeDependency("husky"))
      .containing(nodeDependency("lint-staged"))
      .containing(nodeScript("prepare", "husky"))
      .and()
      .hasFile(".lintstagedrc.cjs")
      .and()
      .hasExecutableFiles(".husky/pre-commit")
      .hasFile(".npmrc");
  }

  @Test
  void shouldBuildModuleForNpm() {
    mockNodeVersion();
    mockNodePackageManagerVersion(NPM, "11.9.9");
    JHipsterModuleProperties properties = defaultProperties(TestFileUtils.tmpDirForTest()).nodePackageManager(NPM).build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModule(module)
      .hasFile("README.md")
      .containing("npm install")
      .and()
      .hasFile("package.json")
      .containing("\"packageManager\": \"npm@11.9.9\"");
  }

  @Test
  void shouldBuildModuleForPnpm() {
    mockNodeVersion();
    mockNodePackageManagerVersion(PNPM, "9.9.9");
    JHipsterModuleProperties properties = defaultProperties(TestFileUtils.tmpDirForTest()).nodePackageManager(PNPM).build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModule(module)
      .hasFile("README.md")
      .containing("pnpm install")
      .and()
      .hasFile("package.json")
      .containing("\"packageManager\": \"pnpm@9.9.9\"");
  }

  private void mockNodeVersion() {
    when(nodeVersions.nodeVersion()).thenReturn(new NodePackageVersion("16.0.0"));
  }

  private void mockNodePackageManagerVersion(NodePackageManager packageManager, String version) {
    when(nodeVersions.packageManagerVersion(packageManager)).thenReturn(new NodePackageVersion(version));
  }

  private static JHipsterModulesFixture.JHipsterModulePropertiesBuilder defaultProperties(String folder) {
    return JHipsterModulesFixture.propertiesBuilder(folder)
      .projectBaseName("testProject")
      .projectName("Test Project")
      .nodePackageManager(NPM)
      .put("endOfLine", "crlf")
      .put("indentSize", 4);
  }
}
