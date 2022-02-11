package tech.jhipster.lite.generator.githubactions.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static tech.jhipster.lite.TestUtils.tmpProject;

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
class GithubActionsDomainServiceTest {

  @Mock
  private ProjectRepository projectRepository;

  @InjectMocks
  private GithubActionsDomainService githubActionsDomainService;

  @Test
  void shouldInit() {
    Project project = tmpProject();

    assertThatCode(() -> githubActionsDomainService.init(project)).doesNotThrowAnyException();
  }

  @Test
  void shouldAddYml() {
    Project project = tmpProject();

    assertThatCode(() -> githubActionsDomainService.addYml(project)).doesNotThrowAnyException();

    verify(projectRepository)
      .template(any(Project.class), eq("githubactions"), eq("github-actions.yml.mustache"), eq(".github/workflows/"));
  }
}
