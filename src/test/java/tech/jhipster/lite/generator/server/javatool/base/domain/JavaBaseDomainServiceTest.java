package tech.jhipster.lite.generator.server.javatool.base.domain;

import static org.mockito.Mockito.*;
import static tech.jhipster.lite.TestUtils.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFilesAsserter;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class JavaBaseDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @InjectMocks
  JavaBaseDomainService javaBaseDomainService;

  @Test
  void shouldAddJavaBase() {
    Project project = tmpProject();

    javaBaseDomainService.addJavaBase(project);

    verify(projectRepository).template(ProjectFilesAsserter.filesCountArgument(13));
    verify(projectRepository, times(2)).template(ProjectFilesAsserter.filesCountArgument(2));
  }
}
