package tech.jhipster.forge.generator.server.springboot.core.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static tech.jhipster.forge.TestUtils.tmpProject;
import static tech.jhipster.forge.TestUtils.tmpProjectWithPomXml;
import static tech.jhipster.forge.common.domain.FileUtils.getPath;
import static tech.jhipster.forge.common.domain.FileUtils.getPathOf;
import static tech.jhipster.forge.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.forge.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.forge.generator.server.springboot.core.domain.SpringBoot.APPLICATION_PROPERTIES;

import java.nio.file.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.FileUtils;
import tech.jhipster.forge.error.domain.GeneratorException;
import tech.jhipster.forge.generator.project.domain.*;

@UnitTest
@ExtendWith(MockitoExtension.class)
class SpringBootDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @Mock
  BuildToolRepository buildToolRepository;

  SpringBootDomainService springBootDomainService;

  @BeforeEach
  void setUp() {
    springBootDomainService = new SpringBootDomainService(projectRepository, buildToolRepository);
  }

  @Test
  void shouldInit() throws Exception {
    Project project = tmpProjectWithPomXml();

    springBootDomainService.init(project);

    verify(buildToolRepository).addParent(any(Project.class), any(Parent.class));
    verify(buildToolRepository, times(3)).addDependency(any(Project.class), any(Dependency.class));
    verify(buildToolRepository).addPlugin(any(Project.class), any(Plugin.class));

    // for application.properties
    verify(projectRepository, times(1)).template(any(Project.class), anyString(), anyString(), anyString());
    // for main class + test
    verify(projectRepository, times(3)).template(any(Project.class), anyString(), anyString(), anyString(), anyString());
  }

  @Test
  void shouldAddSpringBootParent() throws Exception {
    Project project = tmpProjectWithPomXml();

    springBootDomainService.addSpringBootParent(project);

    verify(buildToolRepository).addParent(any(Project.class), any(Parent.class));
  }

  @Test
  void shouldAddSpringBootDependencies() throws Exception {
    Project project = tmpProjectWithPomXml();

    springBootDomainService.addSpringBootDependencies(project);

    verify(buildToolRepository, times(3)).addDependency(any(Project.class), any(Dependency.class));
  }

  @Test
  void shouldAddSpringBootPlugin() throws Exception {
    Project project = tmpProjectWithPomXml();

    springBootDomainService.addSpringBootMavenPlugin(project);

    verify(buildToolRepository).addPlugin(any(Project.class), any(Plugin.class));
  }

  @Test
  void shouldAddProperties() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(getPath(project.getFolder(), MAIN_RESOURCES, "config"));
    Files.copy(
      getPathOf(TEST_RESOURCES, "template/server/springboot/core/application.test.properties"),
      getPathOf(project.getFolder(), MAIN_RESOURCES, "config", APPLICATION_PROPERTIES)
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

  @Test
  void shouldAddPropertiesTest() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(getPath(project.getFolder(), TEST_RESOURCES, "config"));
    Files.copy(
      getPathOf(TEST_RESOURCES, "template/server/springboot/core/application.test.properties"),
      getPathOf(project.getFolder(), TEST_RESOURCES, "config", APPLICATION_PROPERTIES)
    );

    springBootDomainService.addPropertiesTest(project, "server.port", 8080);

    verify(projectRepository).write(any(Project.class), anyString(), anyString(), anyString());
  }

  @Test
  void shouldNotAddPropertiesTest() {
    Project project = tmpProject();

    assertThatThrownBy(() -> springBootDomainService.addPropertiesTest(project, "server.port", 8080))
      .isExactlyInstanceOf(GeneratorException.class);
  }
}
