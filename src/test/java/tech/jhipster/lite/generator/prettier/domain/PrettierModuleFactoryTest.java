package tech.jhipster.lite.generator.prettier.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

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
class PrettierModuleFactoryTest {

  @InjectMocks
  private PrettierModuleFactory factory;

  @Test
  void shouldBuildModuleWithoutPrettierLintStaged() {
    String folder = TestFileUtils.tmpDirForTest();
    JHipsterModuleProperties properties = properties(folder);

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, packageJsonFile(), lintStagedConfigFileWithoutPrettier())
      .hasFiles(".prettierignore")
      .hasFile(".lintstagedrc.cjs")
      .containing(
        """
        module.exports = {
          '*.{md,json,yml,html,css,scss,java,xml,feature}': ['prettier --write'],
          '*.pug': ['eslint --fix', 'prettier --write'],
        };
        """
      )
      .and()
      .hasFile(".prettierrc")
      .containing("tabWidth: 2")
      .containing("endOfLine: 'crlf'")
      .containing("@prettier/plugin-xml")
      .containing("prettier-plugin-gherkin")
      .containing("prettier-plugin-java")
      .containing("prettier-plugin-packagejson")
      .and()
      .hasFile("package.json")
      .containing(nodeDependency("@prettier/plugin-xml"))
      .containing(nodeDependency("prettier"))
      .containing(nodeDependency("prettier-plugin-gherkin"))
      .containing(nodeDependency("prettier-plugin-java"))
      .containing(nodeDependency("prettier-plugin-packagejson"))
      .containing(nodeScript("prettier:check", "prettier --check ."))
      .containing(nodeScript("prettier:format", "prettier --write ."));
  }

  @Test
  void shouldBuildModuleWithEmptyLintStaged() {
    String folder = TestFileUtils.tmpDirForTest();
    JHipsterModuleProperties properties = properties(folder);

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, packageJsonFile(), emptyLintStagedConfigFile())
      .hasFile(".lintstagedrc.cjs")
      .containing(
        """
        module.exports = {
          '*.{md,json,yml,html,css,scss,java,xml,feature}': ['prettier --write'],
        };
        """
      );
  }

  private JHipsterModuleProperties properties(String folder) {
    return JHipsterModulesFixture.propertiesBuilder(folder)
      .projectBaseName("testProject")
      .put("projectName", "Test Project")
      .put("indentSize", 2)
      .put("endOfLine", "crlf")
      .build();
  }
}
