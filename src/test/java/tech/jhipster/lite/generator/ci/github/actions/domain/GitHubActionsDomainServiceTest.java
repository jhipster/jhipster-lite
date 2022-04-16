package tech.jhipster.lite.generator.ci.github.actions.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static tech.jhipster.lite.TestUtils.tmpProjectWithBuildGradle;
import static tech.jhipster.lite.TestUtils.tmpProjectWithPomXml;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class GitHubActionsDomainServiceTest {

  @Mock
  private ProjectRepository projectRepository;

  @InjectMocks
  private GitHubActionsDomainService gitHubActionsDomainService;

  @Test
  void shouldAddGitHubActionsForGradle() {
    Project project = tmpProjectWithBuildGradle();

    assertThatCode(() -> gitHubActionsDomainService.addGitHubActions(project)).doesNotThrowAnyException();

    verify(projectRepository)
      .template(
        any(Project.class),
        eq("ci/github/actions/.github/actions/setup/"),
        eq("action.yml.mustache"),
        eq(".github/actions/setup/")
      );
    verify(projectRepository)
      .template(
        any(Project.class),
        eq("ci/github/actions/.github/workflows/"),
        eq("github-actions-gradle.yml.mustache"),
        eq(".github/workflows/"),
        eq("github-actions.yml")
      );
  }

  @Test
  void shouldAddGitHubActionsForMaven() {
    Project project = tmpProjectWithPomXml();

    assertThatCode(() -> gitHubActionsDomainService.addGitHubActions(project)).doesNotThrowAnyException();

    verify(projectRepository)
      .template(
        any(Project.class),
        eq("ci/github/actions/.github/actions/setup/"),
        eq("action.yml.mustache"),
        eq(".github/actions/setup/")
      );
    verify(projectRepository)
      .template(
        any(Project.class),
        eq("ci/github/actions/.github/workflows/"),
        eq("github-actions-maven.yml.mustache"),
        eq(".github/workflows/"),
        eq("github-actions.yml")
      );
  }
}
