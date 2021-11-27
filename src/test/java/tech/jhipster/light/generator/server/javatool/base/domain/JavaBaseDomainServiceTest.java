package tech.jhipster.light.generator.server.javatool.base.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static tech.jhipster.light.TestUtils.tmpProject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.light.UnitTest;
import tech.jhipster.light.generator.project.domain.Project;
import tech.jhipster.light.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class JavaBaseDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @InjectMocks
  JavaBaseDomainService javaBaseDomainService;

  @Test
  void shouldInit() {
    Project project = tmpProject();

    javaBaseDomainService.init(project);

    verify(projectRepository, times(8)).template(any(Project.class), anyString(), anyString(), anyString());
  }
}
