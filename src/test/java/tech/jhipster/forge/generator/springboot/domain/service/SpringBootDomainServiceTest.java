package tech.jhipster.forge.generator.springboot.domain.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static tech.jhipster.forge.TestUtils.tmpProject;
import static tech.jhipster.forge.TestUtils.tmpProjectWithPomXml;
import static tech.jhipster.forge.common.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.forge.common.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.forge.common.utils.FileUtils.getPath;
import static tech.jhipster.forge.common.utils.FileUtils.getPathOf;
import static tech.jhipster.forge.generator.springboot.domain.service.SpringBoot.APPLICATION_PROPERTIES;

import java.nio.file.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.common.utils.FileUtils;
import tech.jhipster.forge.error.domain.GeneratorException;
import tech.jhipster.forge.generator.springboot.domain.model.Dependency;
import tech.jhipster.forge.generator.springboot.domain.model.Parent;
import tech.jhipster.forge.generator.springboot.domain.model.Plugin;
import tech.jhipster.forge.generator.springboot.domain.usecase.MavenService;

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

    springBootDomainService.init(project);

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

  @Test
  void shouldAddProperties() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(getPath(project.getPath(), MAIN_RESOURCES, "config"));
    Files.copy(
      getPathOf(TEST_RESOURCES, "template/springboot/application.test.properties"),
      getPathOf(project.getPath(), MAIN_RESOURCES, "config", APPLICATION_PROPERTIES)
    );

    springBootDomainService.addProperties(project, "server.port", 8080);

    verify(projectRepository).write(any(Project.class), anyString(), anyString(), anyString());
  }

  @Test
  void shouldNotAddProperties() {
    Project project = tmpProject();

    assertThatThrownBy(() -> springBootDomainService.addProperties(project, "server.port", 8080))
      .isExactlyInstanceOf(GeneratorException.class);
  }
}
