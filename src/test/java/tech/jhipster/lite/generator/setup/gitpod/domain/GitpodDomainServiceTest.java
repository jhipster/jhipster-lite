package tech.jhipster.lite.generator.setup.gitpod.domain;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static tech.jhipster.lite.TestUtils.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class GitpodDomainServiceTest {

  @Mock
  private ProjectRepository projectRepository;

  @InjectMocks
  private GitpodDomainService gitpodDomainService;

  @Test
  void shouldAddGitpodFiles() {
    Project project = tmpProjectWithPackageJson();

    gitpodDomainService.init(project);
    verify(projectRepository, times(2)).add(any(ProjectFile.class));
  }
}
