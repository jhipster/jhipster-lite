package tech.jhipster.forge.generator.server.springboot.core.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static tech.jhipster.forge.TestUtils.tmpProjectWithPomXml;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.forge.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.forge.generator.buildtool.generic.domain.Parent;
import tech.jhipster.forge.generator.buildtool.generic.domain.Plugin;
import tech.jhipster.forge.generator.project.domain.*;

@UnitTest
@ExtendWith(MockitoExtension.class)
class SpringBootDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @Mock
  BuildToolService buildToolService;

  @InjectMocks
  SpringBootDomainService springBootDomainService;

  @Test
  void shouldInit() {
    Project project = tmpProjectWithPomXml();

    springBootDomainService.init(project);

    verify(buildToolService).addParent(any(Project.class), any(Parent.class));
    verify(buildToolService, times(3)).addDependency(any(Project.class), any(Dependency.class));
    verify(buildToolService).addPlugin(any(Project.class), any(Plugin.class));

    // for application.properties
    verify(projectRepository, times(1)).template(any(Project.class), anyString(), anyString(), anyString());
    // for main class + test
    verify(projectRepository, times(3)).template(any(Project.class), anyString(), anyString(), anyString(), anyString());
  }

  @Test
  void shouldAddSpringBootParent() {
    Project project = tmpProjectWithPomXml();

    springBootDomainService.addSpringBootParent(project);

    verify(buildToolService).addParent(any(Project.class), any(Parent.class));
  }

  @Test
  void shouldAddSpringBootDependencies() {
    Project project = tmpProjectWithPomXml();

    springBootDomainService.addSpringBootDependencies(project);

    verify(buildToolService, times(3)).addDependency(any(Project.class), any(Dependency.class));
  }

  @Test
  void shouldAddSpringBootPlugin() {
    Project project = tmpProjectWithPomXml();

    springBootDomainService.addSpringBootMavenPlugin(project);

    verify(buildToolService).addPlugin(any(Project.class), any(Plugin.class));
  }
}
