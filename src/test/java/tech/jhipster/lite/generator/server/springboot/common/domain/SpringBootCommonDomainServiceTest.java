package tech.jhipster.lite.generator.server.springboot.common.domain;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.lite.TestUtils.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.MissingMandatoryValueException;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@UnitTest
@ExtendWith(MockitoExtension.class)
class SpringBootCommonDomainServiceTest {

  @Mock
  private ProjectRepository projectRepository;

  @InjectMocks
  private SpringBootCommonDomainService springBootCommonDomainService;

  @Test
  void shouldGetProperty() {
    Project project = tmpProjectWithSpringBootProperties();

    assertThat(springBootCommonDomainService.getProperty(project, "spring.application.name")).contains("jhlite");
  }

  @Test
  void shouldNotGetProperty() {
    Project project = tmpProjectWithSpringBootProperties();

    assertThat(springBootCommonDomainService.getProperty(project, "bad.key")).isEmpty();
  }

  @Test
  void shouldNotGetPropertyWhenNoPropertiesFile() {
    Project project = tmpProject();

    assertThat(springBootCommonDomainService.getProperty(project, "spring.application.name")).isEmpty();
  }

  @Test
  void shouldNotGetPropertyForNullProject() {
    assertThatThrownBy(() -> springBootCommonDomainService.getProperty(null, "spring.application.name"))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("project");
  }

  @Test
  void shouldNotGetPropertyForNullKey() {
    Project project = tmpProject();
    assertThatThrownBy(() -> springBootCommonDomainService.getProperty(project, null))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("key");
  }

  @Test
  void shouldNotGetPropertyForBlankKey() {
    Project project = tmpProject();
    assertThatThrownBy(() -> springBootCommonDomainService.getProperty(project, " "))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("key");
  }

  @Test
  void shouldNotCheckIfProjectIsSetWithMariaDbOrMySqlDatabaseWithoutProject() {
    assertThatThrownBy(() -> springBootCommonDomainService.isSetWithMySQLOrMariaDBDatabase(null))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("project");
  }

  @Test
  void shouldNotCheckIfProjectIsDatabaseUseSequencesWithoutProject() {
    assertThatThrownBy(() -> springBootCommonDomainService.isDatabaseUseSequences(null))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("project");
  }
}
