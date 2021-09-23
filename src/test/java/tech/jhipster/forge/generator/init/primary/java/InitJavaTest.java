package tech.jhipster.forge.generator.init.primary.java;

import static org.assertj.core.api.Assertions.assertThatCode;
import static tech.jhipster.forge.TestUtils.tmpProject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.init.application.InitApplicationService;

@UnitTest
@ExtendWith(SpringExtension.class)
class InitJavaTest {

  @Mock
  InitApplicationService initApplicationService;

  @InjectMocks
  InitJava initJava;

  @Test
  void shouldInit() {
    Project project = tmpProject();

    assertThatCode(() -> initJava.init(project)).doesNotThrowAnyException();
  }

  @Test
  void shouldAddPackageJson() {
    Project project = tmpProject();

    assertThatCode(() -> initJava.addPackageJson(project)).doesNotThrowAnyException();
  }

  @Test
  void shouldAddReadme() {
    Project project = tmpProject();

    assertThatCode(() -> initJava.addReadme(project)).doesNotThrowAnyException();
  }

  @Test
  void shouldAddGitConfiguration() {
    Project project = tmpProject();

    assertThatCode(() -> initJava.addGitConfiguration(project)).doesNotThrowAnyException();
  }

  @Test
  void shouldAddEditorConfiguration() {
    Project project = tmpProject();

    assertThatCode(() -> initJava.addEditorConfiguration(project)).doesNotThrowAnyException();
  }

  @Test
  void shouldAddPrettier() {
    Project project = tmpProject();

    assertThatCode(() -> initJava.addPrettier(project)).doesNotThrowAnyException();
  }
}
