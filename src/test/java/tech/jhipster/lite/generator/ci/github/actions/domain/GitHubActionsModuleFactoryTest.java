package tech.jhipster.lite.generator.ci.github.actions.domain;

import static org.mockito.Mockito.when;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.gradleBuildFile;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.pomFile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.nodejs.NodePackageVersion;
import tech.jhipster.lite.module.domain.nodejs.NodeVersions;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
@ExtendWith(MockitoExtension.class)
class GitHubActionsModuleFactoryTest {

  @Mock
  private NodeVersions nodeVersions;

  @InjectMocks
  private GitHubActionsModuleFactory factory;

  @Test
  void shouldBuildGitHubActionsMavenModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();
    mockNodeVersion();

    JHipsterModule module = factory.buildGitHubActionsMavenModule(properties);

    assertThatModuleWithFiles(module, pomFile()).hasFile(".github/workflows/github-actions.yml").matchingSavedSnapshot();
  }

  private void mockNodeVersion() {
    when(nodeVersions.nodeVersion()).thenReturn(new NodePackageVersion("44"));
  }

  @Test
  void shouldBuildGitHubActionsGradleModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();
    mockNodeVersion();

    JHipsterModule module = factory.buildGitHubActionsGradleModule(properties);

    assertThatModuleWithFiles(module, gradleBuildFile()).hasFile(".github/workflows/github-actions.yml").matchingSavedSnapshot();
  }
}
