package tech.jhipster.lite.generator.server.springboot.logging.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;

import ch.qos.logback.classic.Level;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class SpringBootLoggingApplicationServiceIT {

  @Autowired
  SpringBootLoggingApplicationService service;

  @Nested
  class LoggingConfigurationIT {

    @Test
    void shouldNotAddLogger() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addLogger(project, "tech.jhipster.web", Level.INFO)).isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddLoggerWithLevel() {
      Project project = tmpProjectWithSpringBootLoggingConfiguration();

      service.addLogger(project, "tech.jhipster.web", Level.DEBUG);

      String applicationProperties = getPath(MAIN_RESOURCES, "logback-spring.xml");
      assertFileContent(project, applicationProperties, "<logger name=\"tech.jhipster.web\" level=\"DEBUG\"/>");
      assertFileContent(project, applicationProperties, "<!-- jhipster-needle-logback-add-logger -->");
    }
  }

  @Nested
  class TestLoggingConfigurationIT {

    @Test
    void shouldNotAddTestLogger() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addLoggerTest(project, "tech.jhipster.web", Level.INFO))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddLoggerWithLevel() {
      Project project = tmpProjectWithSpringBootLoggingConfiguration();

      service.addLoggerTest(project, "tech.jhipster.web", Level.DEBUG);

      String applicationProperties = getPath(TEST_RESOURCES, "logback-test.xml");
      assertFileContent(project, applicationProperties, "<logger name=\"tech.jhipster.web\" level=\"DEBUG\"/>");
      assertFileContent(project, applicationProperties, "<!-- jhipster-needle-logback-add-logger -->");
    }
  }
}
