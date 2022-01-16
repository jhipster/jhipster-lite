package tech.jhipster.lite.generator.server.springboot.common.application;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.common.domain.WordUtils.LF;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;

import java.io.IOException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.common.domain.Level;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;

@IntegrationTest
class SpringBootCommonApplicationServiceIT {

  @Autowired
  SpringBootCommonApplicationService springBootCommonApplicationService;

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Test
  void shouldAddTestLogbackRecorder() {
    Project project = tmpProject();
    project.addConfig(BASE_NAME, "foo");
    project.addConfig(PACKAGE_NAME, "tech.jhipster.bar");
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    springBootCommonApplicationService.addTestLogbackRecorder(project);

    String packageName = project.getPackageName().orElse("com.mycompany.myapp");
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));

    assertFileExist(project, getPath(TEST_JAVA, packageNamePath, "LogbackRecorder.java"));

    assertFileContent(project, getPath(TEST_JAVA, packageNamePath, "LogbackRecorder.java"), "package " + packageName);
  }

  @Nested
  class PropertiesIT {

    @Test
    void shouldNotAddProperties() {
      Project project = tmpProject();

      assertThatThrownBy(() -> springBootCommonApplicationService.addProperties(project, "server.port", 8080))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddPropertiesWithInteger() {
      Project project = tmpProjectWithSpringBootProperties();

      springBootCommonApplicationService.addProperties(project, "server.port", 8080);

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application.properties");
      assertFileContent(project, applicationProperties, "server.port=8080");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-properties");
    }

    @Test
    void shouldAddPropertiesWithBoolean() {
      Project project = tmpProjectWithSpringBootProperties();

      springBootCommonApplicationService.addProperties(project, "spring.jmx.enabled", false);

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application.properties");
      assertFileContent(project, applicationProperties, "spring.jmx.enabled=false");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-properties");
    }

    @Test
    void shouldAddPropertiesWithString() {
      Project project = tmpProjectWithSpringBootProperties();

      springBootCommonApplicationService.addProperties(project, "jhipster.application", "jhlite");

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application.properties");
      assertFileContent(project, applicationProperties, "jhipster.application=jhlite");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-properties");
    }

    @Test
    void shouldNotAddPropertiesWhenNoApplicationProperties() {
      Project project = tmpProject();

      assertThatThrownBy(() -> springBootCommonApplicationService.addProperties(project, "jhipster.application", "jhlite"))
        .isExactlyInstanceOf(GeneratorException.class);
    }
  }

  @Nested
  class FastPropertiesIT {

    @Test
    void shouldNotAddPropertiesFast() {
      Project project = tmpProject();

      assertThatThrownBy(() -> springBootCommonApplicationService.addPropertiesFast(project, "specific.config.fast", "chips"))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddPropertiesFastWithInteger() {
      Project project = tmpProjectWithSpringBootProperties();

      springBootCommonApplicationService.addPropertiesFast(project, "server.port", 8080);

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application-fast.properties");
      assertFileContent(project, applicationProperties, "server.port=8080");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-fast-properties");
    }

    @Test
    void shouldAddPropertiesFastWithBoolean() {
      Project project = tmpProjectWithSpringBootProperties();

      springBootCommonApplicationService.addPropertiesFast(project, "spring.jmx.enabled", false);

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application-fast.properties");
      assertFileContent(project, applicationProperties, "spring.jmx.enabled=false");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-fast-properties");
    }

    @Test
    void shouldAddPropertiesFastWithString() {
      Project project = tmpProjectWithSpringBootProperties();

      springBootCommonApplicationService.addPropertiesFast(project, "jhipster.application", "jhlite");

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application-fast.properties");
      assertFileContent(project, applicationProperties, "jhipster.application=jhlite");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-fast-properties");
    }

    @Test
    void shouldNotAddPropertiesFastWhenNoApplicationProperties() {
      Project project = tmpProject();

      assertThatThrownBy(() -> springBootCommonApplicationService.addPropertiesFast(project, "jhipster.application", "jhlite"))
        .isExactlyInstanceOf(GeneratorException.class);
    }
  }

  @Nested
  class TestPropertiesIT {

    @Test
    void shouldNotAddPropertiesTest() {
      Project project = tmpProject();

      assertThatThrownBy(() -> springBootCommonApplicationService.addPropertiesTest(project, "server.port", 8080))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddPropertiesTestWithInteger() {
      Project project = tmpProjectWithSpringBootProperties();

      springBootCommonApplicationService.addPropertiesTest(project, "server.port", 8080);

      String applicationProperties = getPath(TEST_RESOURCES, "config/application.properties");
      assertFileContent(project, applicationProperties, "server.port=8080");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-test-properties");
    }

    @Test
    void shouldAddPropertiesTestWithBoolean() {
      Project project = tmpProjectWithSpringBootProperties();

      springBootCommonApplicationService.addPropertiesTest(project, "spring.jmx.enabled", false);

      String applicationProperties = getPath(TEST_RESOURCES, "config/application.properties");
      assertFileContent(project, applicationProperties, "spring.jmx.enabled=false");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-test-properties");
    }

    @Test
    void shouldAddPropertiesTestWithString() {
      Project project = tmpProjectWithSpringBootProperties();

      springBootCommonApplicationService.addPropertiesTest(project, "jhipster.application", "jhlite");

      String applicationProperties = getPath(TEST_RESOURCES, "config/application.properties");
      assertFileContent(project, applicationProperties, "jhipster.application=jhlite");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-test-properties");
    }

    @Test
    void shouldNotAddPropertiesTestWhenNoApplicationProperties() {
      Project project = tmpProject();

      assertThatThrownBy(() -> springBootCommonApplicationService.addPropertiesTest(project, "jhipster.application", "jhlite"))
        .isExactlyInstanceOf(GeneratorException.class);
    }
  }

  @Nested
  class PropertiesNewLineIT {

    @Test
    void shouldNotAddPropertiesNewLine() {
      Project project = tmpProject();

      assertThatThrownBy(() -> springBootCommonApplicationService.addPropertiesNewLine(project))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddPropertiesNewLine() throws IOException {
      Project project = tmpProjectWithSpringBootProperties();

      springBootCommonApplicationService.addProperties(project, "new.property", "value");
      springBootCommonApplicationService.addPropertiesNewLine(project);

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application.properties");
      assertFileContentRegexp(project, applicationProperties, "new.property=value" + LF + LF + "# jhipster-needle-application-properties");
    }
  }

  @Nested
  class PropertiesFastNewLineIT {

    @Test
    void shouldNotAddPropertiesFastNewLine() {
      Project project = tmpProject();

      assertThatThrownBy(() -> springBootCommonApplicationService.addPropertiesFastNewLine(project))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddPropertiesFastNewLine() throws IOException {
      Project project = tmpProjectWithSpringBootProperties();

      springBootCommonApplicationService.addPropertiesFast(project, "new.property", "value");
      springBootCommonApplicationService.addPropertiesFastNewLine(project);

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application-fast.properties");
      assertFileContentRegexp(
        project,
        applicationProperties,
        "new.property=value" + LF + LF + "# jhipster-needle-application-fast-properties"
      );
    }
  }

  @Nested
  class PropertiesTestNewLineIT {

    @Test
    void shouldNotAddPropertiesTestNewLine() {
      Project project = tmpProject();

      assertThatThrownBy(() -> springBootCommonApplicationService.addPropertiesTestNewLine(project))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddPropertiesTestNewLine() throws IOException {
      Project project = tmpProjectWithSpringBootProperties();

      springBootCommonApplicationService.addPropertiesTest(project, "new.property", "value");
      springBootCommonApplicationService.addPropertiesTestNewLine(project);

      String applicationProperties = getPath(TEST_RESOURCES, "config/application.properties");
      assertFileContentRegexp(
        project,
        applicationProperties,
        "new.property=value" + LF + LF + "# jhipster-needle-application-test-properties"
      );
    }
  }

  @Nested
  class PropertiesCommentIT {

    @Test
    void shouldNotAddPropertiesComment() {
      Project project = tmpProject();

      assertThatThrownBy(() -> springBootCommonApplicationService.addPropertiesComment(project, "new comment"))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddPropertiesComment() {
      Project project = tmpProjectWithSpringBootProperties();

      springBootCommonApplicationService.addPropertiesComment(project, "new comment");

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application.properties");
      assertFileContent(project, applicationProperties, "# new comment");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-properties");
    }
  }

  @Nested
  class PropertiesFastCommentIT {

    @Test
    void shouldNotAddPropertiesFastComment() {
      Project project = tmpProject();

      assertThatThrownBy(() -> springBootCommonApplicationService.addPropertiesFastComment(project, "new comment"))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddPropertiesFastComment() {
      Project project = tmpProjectWithSpringBootProperties();

      springBootCommonApplicationService.addPropertiesFastComment(project, "new comment");

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application-fast.properties");
      assertFileContent(project, applicationProperties, "# new comment");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-fast-properties");
    }
  }

  @Nested
  class PropertiesTestCommentIT {

    @Test
    void shouldNotAddPropertiesTestComment() {
      Project project = tmpProject();

      assertThatThrownBy(() -> springBootCommonApplicationService.addPropertiesTestComment(project, "new comment"))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddPropertiesTestComment() {
      Project project = tmpProjectWithSpringBootProperties();

      springBootCommonApplicationService.addPropertiesTestComment(project, "new comment");

      String applicationProperties = getPath(TEST_RESOURCES, "config/application.properties");
      assertFileContent(project, applicationProperties, "# new comment");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-test-properties");
    }
  }

  @Nested
  class LoggingConfigurationIT {

    @Test
    void shouldNotAddLogger() {
      Project project = tmpProject();

      assertThatThrownBy(() -> springBootCommonApplicationService.addLogger(project, "tech.jhipster.web", Level.INFO))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddLoggerWithLevel() {
      Project project = tmpProjectWithSpringBootLoggingConfiguration();

      springBootCommonApplicationService.addLogger(project, "tech.jhipster.web", Level.DEBUG);

      String applicationProperties = getPath(MAIN_RESOURCES, "logback-spring.xml");
      assertFileContent(project, applicationProperties, "<logger name=\"tech.jhipster.web\" level=\"DEBUG\" />");
      assertFileContent(project, applicationProperties, "<!-- jhipster-needle-logback-add-log -->");
    }
  }

  @Nested
  class TestLoggingConfigurationIT {

    @Test
    void shouldNotAddTestLogger() {
      Project project = tmpProject();

      assertThatThrownBy(() -> springBootCommonApplicationService.addLoggerTest(project, "tech.jhipster.web", Level.INFO))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddLoggerWithLevel() {
      Project project = tmpProjectWithSpringBootLoggingConfiguration();

      springBootCommonApplicationService.addLoggerTest(project, "tech.jhipster.web", Level.DEBUG);

      String applicationProperties = getPath(TEST_RESOURCES, "logback.xml");
      assertFileContent(project, applicationProperties, "<logger name=\"tech.jhipster.web\" level=\"DEBUG\" />");
      assertFileContent(project, applicationProperties, "<!-- jhipster-needle-logback-add-log -->");
    }
  }
}
