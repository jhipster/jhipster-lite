package tech.jhipster.lite.generator.server.javatool.arch.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static tech.jhipster.lite.TestUtils.tmpProject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class JavaArchUnitDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @InjectMocks
  JavaArchUnitDomainService javaArchUnitDomainService;

  @Mock
  BuildToolService buildToolService;

  @Test
  void shouldInit() {
    Project project = tmpProject();

    javaArchUnitDomainService.init(project);

    verify(projectRepository, times(3)).template(any(Project.class), anyString(), anyString(), anyString());
    verify(buildToolService).addProperty(any(Project.class), anyString(), anyString());
    verify(buildToolService).addDependency(any(Project.class), any(Dependency.class));
  }
}
