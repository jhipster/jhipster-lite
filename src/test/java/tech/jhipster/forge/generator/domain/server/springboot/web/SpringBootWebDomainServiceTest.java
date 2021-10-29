package tech.jhipster.forge.generator.domain.server.springboot.web;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static tech.jhipster.forge.TestUtils.tmpProjectWithPomXml;
import static tech.jhipster.forge.common.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.forge.common.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.forge.common.utils.FileUtils.getPath;
import static tech.jhipster.forge.common.utils.FileUtils.getPathOf;
import static tech.jhipster.forge.generator.domain.server.springboot.core.SpringBoot.APPLICATION_PROPERTIES;

import java.nio.file.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.utils.FileUtils;
import tech.jhipster.forge.generator.domain.buildtool.Dependency;
import tech.jhipster.forge.generator.domain.buildtool.maven.MavenService;
import tech.jhipster.forge.generator.domain.server.springboot.core.SpringBootService;

@UnitTest
@ExtendWith(MockitoExtension.class)
class SpringBootWebDomainServiceTest {

  @Mock
  MavenService mavenService;

  @Mock
  SpringBootService springBootService;

  SpringBootWebDomainService springBootWebDomainService;

  @BeforeEach
  void setUp() {
    springBootWebDomainService = new SpringBootWebDomainService(mavenService, springBootService);
  }

  @Test
  void shouldInit() throws Exception {
    Project project = tmpProjectWithPomXml();
    FileUtils.createFolder(getPath(project.getPath(), MAIN_RESOURCES, "config"));
    Files.copy(
      getPathOf(TEST_RESOURCES, "template/springboot/application.test.properties"),
      getPathOf(project.getPath(), MAIN_RESOURCES, "config", APPLICATION_PROPERTIES)
    );

    springBootWebDomainService.init(project);

    verify(mavenService).addDependency(any(Project.class), any(Dependency.class));
    verify(springBootService).addProperties(any(Project.class), anyString(), any());
  }

  @Test
  void shouldAddSpringBootWeb() throws Exception {
    Project project = tmpProjectWithPomXml();
    FileUtils.createFolder(getPath(project.getPath(), MAIN_RESOURCES, "config"));
    Files.copy(
      getPathOf(TEST_RESOURCES, "template/springboot/application.test.properties"),
      getPathOf(project.getPath(), MAIN_RESOURCES, "config", APPLICATION_PROPERTIES)
    );

    springBootWebDomainService.addSpringBootWeb(project);

    verify(mavenService).addDependency(any(Project.class), any(Dependency.class));
    verify(springBootService).addProperties(any(Project.class), anyString(), any());
  }

  @Test
  void shouldAddSpringBootWebWithInvalidServerPort() throws Exception {
    Project project = tmpProjectWithPomXml();
    FileUtils.createFolder(getPath(project.getPath(), MAIN_RESOURCES, "config"));
    Files.copy(
      getPathOf(TEST_RESOURCES, "template/springboot/application.test.properties"),
      getPathOf(project.getPath(), MAIN_RESOURCES, "config", APPLICATION_PROPERTIES)
    );
    project.addConfig("serverPort", "chips");

    springBootWebDomainService.addSpringBootWeb(project);

    verify(mavenService).addDependency(any(Project.class), any(Dependency.class));
    verify(springBootService).addProperties(any(Project.class), anyString(), any());
  }

  @Test
  void shouldAddSpringBootUndertow() throws Exception {
    Project project = tmpProjectWithPomXml();
    FileUtils.createFolder(getPath(project.getPath(), MAIN_RESOURCES, "config"));
    Files.copy(
      getPathOf(TEST_RESOURCES, "template/springboot/application.test.properties"),
      getPathOf(project.getPath(), MAIN_RESOURCES, "config", APPLICATION_PROPERTIES)
    );

    springBootWebDomainService.addSpringBootUndertow(project);

    verify(mavenService).addDependency(any(Project.class), any(Dependency.class), anyList());
    verify(mavenService).addDependency(any(Project.class), any(Dependency.class));
    verify(springBootService).addProperties(any(Project.class), anyString(), any());
  }
}
