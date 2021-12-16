package tech.jhipster.lite.generator.server.springboot.logging.domain;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static tech.jhipster.lite.TestUtils.tmpProjectWithSpringBootLoggingConfiguration;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.*;

import ch.qos.logback.classic.Level;
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
class SpringBootLoggingDomainServiceTest {

  @Mock
  ProjectRepository projectRepository;

  @InjectMocks
  SpringBootLoggingDomainService springBootLoggingDomainService;

  @Test
  void shouldAddProperties() {
    Project project = tmpProjectWithSpringBootLoggingConfiguration();

    springBootLoggingDomainService.addLogger(project, "tech.jhipster.lite", Level.ERROR);

    verify(projectRepository)
      .replaceText(
        any(Project.class),
        contains("main"),
        eq(LOGGING_CONFIGURATION),
        eq(NEEDLE_LOGBACK_LOGGER),
        contains(NEEDLE_LOGBACK_LOGGER)
      );
  }

  @Test
  void shouldAddPropertiesTest() {
    Project project = tmpProjectWithSpringBootLoggingConfiguration();

    springBootLoggingDomainService.addLoggerTest(project, "tech.jhipster.lite", Level.ERROR);

    verify(projectRepository)
      .replaceText(
        any(Project.class),
        contains("test"),
        eq(LOGGING_TEST_CONFIGURATION),
        eq(NEEDLE_LOGBACK_LOGGER),
        contains(NEEDLE_LOGBACK_LOGGER)
      );
  }
}
