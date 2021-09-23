package tech.jhipster.forge.generator.springboot.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static tech.jhipster.forge.TestUtils.tmpProjectWithPomXml;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.generator.maven.domain.MavenService;
import tech.jhipster.forge.generator.shared.domain.Dependency;
import tech.jhipster.forge.generator.shared.domain.Parent;
import tech.jhipster.forge.generator.shared.domain.Plugin;

@UnitTest
@ExtendWith(MockitoExtension.class)
class SpringBootDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @Mock
  MavenService mavenService;

  SpringBootDomainService springBootDomainService;

  @BeforeEach
  void setUp() {
    springBootDomainService = new SpringBootDomainService(projectRepository, mavenService);
  }

  @Test
  void shouldAddSpringBoot() throws Exception {
    Project project = tmpProjectWithPomXml();

    springBootDomainService.addSpringBoot(project);

    verify(mavenService).addParent(any(Project.class), any(Parent.class));
    verify(mavenService, times(3)).addDependency(any(Project.class), any(Dependency.class));
    verify(mavenService).addPlugin(any(Project.class), any(Plugin.class));
  }

  @Test
  void shouldAddSpringBootParent() throws Exception {
    Project project = tmpProjectWithPomXml();

    springBootDomainService.addSpringBootParent(project);

    verify(mavenService).addParent(any(Project.class), any(Parent.class));
  }

  @Test
  void shouldAddSpringBootDependencies() throws Exception {
    Project project = tmpProjectWithPomXml();

    springBootDomainService.addSpringBootDependencies(project);

    verify(mavenService, times(3)).addDependency(any(Project.class), any(Dependency.class));
  }

  @Test
  void shouldAddSpringBootPlugin() throws Exception {
    Project project = tmpProjectWithPomXml();

    springBootDomainService.addSpringBootMavenPlugin(project);

    verify(mavenService).addPlugin(any(Project.class), any(Plugin.class));
  }
}
