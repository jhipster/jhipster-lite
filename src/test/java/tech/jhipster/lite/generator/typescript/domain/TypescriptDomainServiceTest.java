package tech.jhipster.lite.generator.typescript.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static tech.jhipster.lite.TestUtils.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class TypescriptDomainServiceTest {

  @InjectMocks
  TypescriptDomainService typescriptDomainService;

  @Mock
  NpmService npmService;

  @Mock
  ProjectRepository projectRepository;

  @Test
  void shouldInit() {
    Project project = tmpProject();
    when(npmService.getVersionInCommon(anyString())).thenReturn(Optional.of("0.0.0"));

    typescriptDomainService.init(project);

    verify(npmService, times(10)).addDevDependency(any(Project.class), anyString(), anyString());
    verify(npmService, times(5)).addScript(any(Project.class), anyString(), anyString());

    verify(projectRepository, times(3)).add(any(Project.class), anyString(), anyString());
  }
}
