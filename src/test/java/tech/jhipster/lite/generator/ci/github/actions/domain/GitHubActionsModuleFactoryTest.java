package tech.jhipster.lite.generator.ci.github.actions.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class GitHubActionsModuleFactoryTest {

  private static final GitHubActionsModuleFactory factory = new GitHubActionsModuleFactory();

  @Test
  void shouldBuildGitHubActionsMavenModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildGitHubActionsMavenModule(properties);

    assertThatModule(module).hasFiles(".github/actions/setup/action.yml", ".github/workflows/github-actions.yml");
  }

  @Test
  void shouldBuildGitHubActionsGradleModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildGitHubActionsGradleModule(properties);

    assertThatModule(module).hasFiles(".github/actions/setup/action.yml", ".github/workflows/github-actions.yml");
  }
}
