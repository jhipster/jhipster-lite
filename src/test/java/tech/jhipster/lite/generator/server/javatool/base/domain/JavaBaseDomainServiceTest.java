package tech.jhipster.lite.generator.server.javatool.base.domain;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
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
class JavaBaseDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @InjectMocks
  JavaBaseDomainService javaBaseDomainService;

  @Test
  void shouldAddJavaBase() {
    Project project = tmpProject();

    javaBaseDomainService.addJavaBase(project);

    verify(projectRepository, times(17)).template(any(Project.class), anyString(), anyString(), anyString());
    verify(projectRepository).template(any(Project.class), anyString(), anyString(), anyString(), eq("JhipsterCollections.java"));
    verify(projectRepository).template(any(Project.class), anyString(), anyString(), anyString(), eq("JhipsterCollectionsTest.java"));
  }
}
