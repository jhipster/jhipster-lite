package tech.jhipster.lite.module.infrastructure.secondary.javadependency.gradle;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.shared.error.domain.ErrorStatus;

@UnitTest
class UnableCreateFolderExceptionTest {

  @Test
  void shouldGetExceptionInformation() {
    RuntimeException cause = new RuntimeException();
    UnableCreateFolderException exception = new UnableCreateFolderException("/buildSrc/src/main/kotlin", cause);

    assertThat(exception.getMessage()).isEqualTo("Unable to create folder(s): /buildSrc/src/main/kotlin");
    assertThat(exception.getCause()).isEqualTo(cause);
    assertThat(exception.key()).isEqualTo(GradleDependencyErrorKey.UNABLE_CREATE_FOLDER);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
  }
}
