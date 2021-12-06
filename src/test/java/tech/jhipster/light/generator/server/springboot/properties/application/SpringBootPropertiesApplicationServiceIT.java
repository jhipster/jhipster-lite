package tech.jhipster.light.generator.server.springboot.properties.application;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.light.TestUtils.*;
import static tech.jhipster.light.common.domain.FileUtils.getPath;
import static tech.jhipster.light.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.light.generator.project.domain.Constants.TEST_RESOURCES;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.light.IntegrationTest;
import tech.jhipster.light.error.domain.GeneratorException;
import tech.jhipster.light.generator.project.domain.Project;

@IntegrationTest
class SpringBootPropertiesApplicationServiceIT {

  @Autowired
  SpringBootPropertiesApplicationService service;

  @Nested
  class Properties {

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

      service.addProperties(project, "jhipster.application", "jhlight");

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application.properties");
      assertFileContent(project, applicationProperties, "jhipster.application=jhlight");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-properties");
    }

    @Test
    void shouldNotAddPropertiesWhenNoApplicationProperties() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addProperties(project, "jhipster.application", "jhlight"))
        .isExactlyInstanceOf(GeneratorException.class);
    }
  }

  @Nested
  class FastProperties {

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

      service.addPropertiesFast(project, "jhipster.application", "jhlight");

      String applicationProperties = getPath(MAIN_RESOURCES, "config/application-fast.properties");
      assertFileContent(project, applicationProperties, "jhipster.application=jhlight");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-fast-properties");
    }

    @Test
    void shouldNotAddPropertiesFastWhenNoApplicationProperties() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addPropertiesFast(project, "jhipster.application", "jhlight"))
        .isExactlyInstanceOf(GeneratorException.class);
    }
  }

  @Nested
  class TestProperties {

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

      service.addPropertiesTest(project, "jhipster.application", "jhlight");

      String applicationProperties = getPath(TEST_RESOURCES, "config/application.properties");
      assertFileContent(project, applicationProperties, "jhipster.application=jhlight");
      assertFileContent(project, applicationProperties, "# jhipster-needle-application-test-properties");
    }

    @Test
    void shouldNotAddPropertiesTestWhenNoApplicationProperties() {
      Project project = tmpProject();

      assertThatThrownBy(() -> service.addPropertiesTest(project, "jhipster.application", "jhlight"))
        .isExactlyInstanceOf(GeneratorException.class);
    }
  }
}
