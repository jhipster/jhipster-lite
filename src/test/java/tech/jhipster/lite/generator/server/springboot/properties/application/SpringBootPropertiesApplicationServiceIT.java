package tech.jhipster.lite.generator.server.springboot.properties.application;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class SpringBootPropertiesApplicationServiceIT {

  @Autowired
  SpringBootPropertiesApplicationService service;

  @Nested
  class PropertiesIT {

    @Test
    void shouldNotAddProperties() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addProperties(project, "server.port", 8080)).isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddPropertiesWithInteger() {
      Project project = tmpProjectWithSpringBootProperties();

      service.addProperties(project, "server.port", 8080);

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application.properties");
      assertFileContent(project, applicationProperties, "server.port=8080");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-properties");
    }

    @Test
    void shouldAddPropertiesWithBoolean() {
      Project project = tmpProjectWithSpringBootProperties();

      service.addProperties(project, "spring.jmx.enabled", false);

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application.properties");
      assertFileContent(project, applicationProperties, "spring.jmx.enabled=false");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-properties");
    }

    @Test
    void shouldAddPropertiesWithString() {
      Project project = tmpProjectWithSpringBootProperties();

      service.addProperties(project, "jhipster.application", "jhlite");

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application.properties");
      assertFileContent(project, applicationProperties, "jhipster.application=jhlite");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-properties");
    }

    @Test
    void shouldNotAddPropertiesWhenNoApplicationProperties() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addProperties(project, "jhipster.application", "jhlite"))
        .isExactlyInstanceOf(GeneratorException.class);
    }
  }

  @Nested
  class FastPropertiesIT {

    @Test
    void shouldNotAddPropertiesFast() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addPropertiesFast(project, "specific.config.fast", "chips"))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddPropertiesFastWithInteger() {
      Project project = tmpProjectWithSpringBootProperties();

      service.addPropertiesFast(project, "server.port", 8080);

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application-fast.properties");
      assertFileContent(project, applicationProperties, "server.port=8080");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-fast-properties");
    }

    @Test
    void shouldAddPropertiesFastWithBoolean() {
      Project project = tmpProjectWithSpringBootProperties();

      service.addPropertiesFast(project, "spring.jmx.enabled", false);

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application-fast.properties");
      assertFileContent(project, applicationProperties, "spring.jmx.enabled=false");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-fast-properties");
    }

    @Test
    void shouldAddPropertiesFastWithString() {
      Project project = tmpProjectWithSpringBootProperties();

      service.addPropertiesFast(project, "jhipster.application", "jhlite");

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application-fast.properties");
      assertFileContent(project, applicationProperties, "jhipster.application=jhlite");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-fast-properties");
    }

    @Test
    void shouldNotAddPropertiesFastWhenNoApplicationProperties() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addPropertiesFast(project, "jhipster.application", "jhlite"))
        .isExactlyInstanceOf(GeneratorException.class);
    }
  }

  @Nested
  class TestPropertiesIT {

    @Test
    void shouldNotAddPropertiesTest() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addPropertiesTest(project, "server.port", 8080)).isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddPropertiesTestWithInteger() {
      Project project = tmpProjectWithSpringBootProperties();

      service.addPropertiesTest(project, "server.port", 8080);

      String applicationProperties = getPath(TEST_RESOURCES, "config/application.properties");
      assertFileContent(project, applicationProperties, "server.port=8080");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-test-properties");
    }

    @Test
    void shouldAddPropertiesTestWithBoolean() {
      Project project = tmpProjectWithSpringBootProperties();

      service.addPropertiesTest(project, "spring.jmx.enabled", false);

      String applicationProperties = getPath(TEST_RESOURCES, "config/application.properties");
      assertFileContent(project, applicationProperties, "spring.jmx.enabled=false");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-test-properties");
    }

    @Test
    void shouldAddPropertiesTestWithString() {
      Project project = tmpProjectWithSpringBootProperties();

      service.addPropertiesTest(project, "jhipster.application", "jhlite");

      String applicationProperties = getPath(TEST_RESOURCES, "config/application.properties");
      assertFileContent(project, applicationProperties, "jhipster.application=jhlite");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-test-properties");
    }

    @Test
    void shouldNotAddPropertiesTestWhenNoApplicationProperties() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addPropertiesTest(project, "jhipster.application", "jhlite"))
        .isExactlyInstanceOf(GeneratorException.class);
    }
  }

  @Nested
  class BootstrapPropertiesIT {

    @Test
    void shouldNotAddBootstrapProperties() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addBootstrapProperties(project, "server.port", 8080)).isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddBootstrapPropertiesWithInteger() {
      Project project = tmpProjectWithSpringCloudBootstrapProperties();

      service.addBootstrapProperties(project, "server.port", 8080);

      String applicationProperties = getPath(MAIN_RESOURCES, "config/bootstrap.properties");
      assertFileContent(project, applicationProperties, "server.port=8080");
      assertFileContent(project, applicationProperties, "# jhipster-needle-bootstrap-properties");
    }

    @Test
    void shouldAddBootstrapPropertiesWithBoolean() {
      Project project = tmpProjectWithSpringCloudBootstrapProperties();

      service.addBootstrapProperties(project, "spring.jmx.enabled", false);

      String applicationProperties = getPath(MAIN_RESOURCES, "config/bootstrap.properties");
      assertFileContent(project, applicationProperties, "spring.jmx.enabled=false");
      assertFileContent(project, applicationProperties, "# jhipster-needle-bootstrap-properties");
    }

    @Test
    void shouldAddBootstrapPropertiesWithString() {
      Project project = tmpProjectWithSpringCloudBootstrapProperties();

      service.addBootstrapProperties(project, "jhipster.application", "jhlite");

      String applicationProperties = getPath(MAIN_RESOURCES, "config/bootstrap.properties");
      assertFileContent(project, applicationProperties, "jhipster.application=jhlite");
      assertFileContent(project, applicationProperties, "# jhipster-needle-bootstrap-properties");
    }

    @Test
    void shouldNotAddBootstrapPropertiesWhenNoApplicationProperties() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addBootstrapProperties(project, "jhipster.application", "jhlite"))
        .isExactlyInstanceOf(GeneratorException.class);
    }
  }

  @Nested
  class BootstrapFastPropertiesIT {

    @Test
    void shouldNotAddBootstrapFastProperties() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addBootstrapPropertiesFast(project, "server.port", 8080))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddBootstrapFastPropertiesWithInteger() {
      Project project = tmpProjectWithSpringCloudBootstrapProperties();

      service.addBootstrapPropertiesFast(project, "server.port", 8080);

      String applicationProperties = getPath(MAIN_RESOURCES, "config/bootstrap-fast.properties");
      assertFileContent(project, applicationProperties, "server.port=8080");
      assertFileContent(project, applicationProperties, "# jhipster-needle-bootstrap-fast-properties");
    }

    @Test
    void shouldAddBootstrapFastPropertiesWithBoolean() {
      Project project = tmpProjectWithSpringCloudBootstrapProperties();

      service.addBootstrapPropertiesFast(project, "spring.jmx.enabled", false);

      String applicationProperties = getPath(MAIN_RESOURCES, "config/bootstrap-fast.properties");
      assertFileContent(project, applicationProperties, "spring.jmx.enabled=false");
      assertFileContent(project, applicationProperties, "# jhipster-needle-bootstrap-fast-properties");
    }

    @Test
    void shouldAddBootstrapFastPropertiesWithString() {
      Project project = tmpProjectWithSpringCloudBootstrapProperties();

      service.addBootstrapPropertiesFast(project, "jhipster.application", "jhlite");

      String applicationProperties = getPath(MAIN_RESOURCES, "config/bootstrap-fast.properties");
      assertFileContent(project, applicationProperties, "jhipster.application=jhlite");
      assertFileContent(project, applicationProperties, "# jhipster-needle-bootstrap-fast-properties");
    }

    @Test
    void shouldNotAddBootstrapFastPropertiesWhenNoApplicationProperties() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addBootstrapPropertiesFast(project, "jhipster.application", "jhlite"))
        .isExactlyInstanceOf(GeneratorException.class);
    }
  }

  @Nested
  class BootstrapPropertiesTestIT {

    @Test
    void shouldNotAddBootstrapPropertiesTest() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addBootstrapPropertiesTest(project, "server.port", 8080))
        .isExactlyInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldAddBootstrapPropertiesTestWithInteger() {
      Project project = tmpProjectWithSpringCloudBootstrapProperties();

      service.addBootstrapPropertiesTest(project, "server.port", 8080);

      String applicationProperties = getPath(TEST_RESOURCES, "config/bootstrap.properties");
      assertFileContent(project, applicationProperties, "server.port=8080");
      assertFileContent(project, applicationProperties, "# jhipster-needle-bootstrap-test-properties");
    }

    @Test
    void shouldAddBootstrapPropertiesTestWithBoolean() {
      Project project = tmpProjectWithSpringCloudBootstrapProperties();

      service.addBootstrapPropertiesTest(project, "spring.jmx.enabled", false);

      String applicationProperties = getPath(TEST_RESOURCES, "config/bootstrap.properties");
      assertFileContent(project, applicationProperties, "spring.jmx.enabled=false");
      assertFileContent(project, applicationProperties, "# jhipster-needle-bootstrap-test-properties");
    }

    @Test
    void shouldAddBootstrapPropertiesTestWithString() {
      Project project = tmpProjectWithSpringCloudBootstrapProperties();

      service.addBootstrapPropertiesTest(project, "jhipster.application", "jhlite");

      String applicationProperties = getPath(TEST_RESOURCES, "config/bootstrap.properties");
      assertFileContent(project, applicationProperties, "jhipster.application=jhlite");
      assertFileContent(project, applicationProperties, "# jhipster-needle-bootstrap-test-properties");
    }

    @Test
    void shouldNotAddBootstrapPropertiesTestWhenNoApplicationProperties() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addBootstrapPropertiesTest(project, "jhipster.application", "jhlite"))
        .isExactlyInstanceOf(GeneratorException.class);
    }
  }
}
