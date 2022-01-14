package tech.jhipster.lite.generator.server.springboot.common.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static tech.jhipster.lite.TestUtils.tmpProject;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.common.domain.FileUtils.getPathOf;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.APPLICATION_FAST_PROPERTIES;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.APPLICATION_PROPERTIES;

import java.nio.file.Files;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class SpringBootCommonDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @InjectMocks
  SpringBootCommonDomainService springBootCommonDomainService;

  @Test
  void shouldAddProperties() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(getPath(project.getFolder(), MAIN_RESOURCES, "config"));
    Files.copy(
      getPathOf(TEST_RESOURCES, "generator/server/springboot/core/application.src.properties"),
      getPathOf(project.getFolder(), MAIN_RESOURCES, "config", APPLICATION_PROPERTIES)
    );

    springBootCommonDomainService.addProperties(project, "server.port", 8080);

    verify(projectRepository).replaceText(any(Project.class), anyString(), anyString(), anyString(), anyString());
  }

  @Test
  void shouldAddPropertiesFast() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(getPath(project.getFolder(), MAIN_RESOURCES, "config"));
    Files.copy(
      getPathOf(TEST_RESOURCES, "generator/server/springboot/core/application.src.fast.properties"),
      getPathOf(project.getFolder(), MAIN_RESOURCES, "config", APPLICATION_FAST_PROPERTIES)
    );

    springBootCommonDomainService.addPropertiesFast(project, "specific.config.fast", "chips");

    verify(projectRepository).replaceText(any(Project.class), anyString(), anyString(), anyString(), anyString());
  }

  @Test
  void shouldAddPropertiesTest() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(getPath(project.getFolder(), TEST_RESOURCES, "config"));
    Files.copy(
      getPathOf(TEST_RESOURCES, "generator/server/springboot/core/application.test.properties"),
      getPathOf(project.getFolder(), TEST_RESOURCES, "config", APPLICATION_PROPERTIES)
    );

    springBootCommonDomainService.addPropertiesTest(project, "server.port", 8080);

    verify(projectRepository).replaceText(any(Project.class), anyString(), anyString(), anyString(), anyString());
  }
}
