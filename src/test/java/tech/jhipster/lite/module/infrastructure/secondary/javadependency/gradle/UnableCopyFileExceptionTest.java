package tech.jhipster.lite.module.infrastructure.secondary.javadependency.gradle;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.shared.error.domain.ErrorStatus;

@UnitTest
class UnableCopyFileExceptionTest {

  @Test
  void shouldGetExceptionInformation() {
    RuntimeException cause = new RuntimeException();
    UnableCopyFileException exception = new UnableCopyFileException("src/test/resources/test.txt", "dest/test.txt", cause);

    assertThat(exception.getMessage()).isEqualTo("Unable to copy file: src/test/resources/test.txt to dest/test.txt");
    assertThat(exception.getCause()).isEqualTo(cause);
    assertThat(exception.key()).isEqualTo(GradleDependencyErrorKey.UNABLE_COPY_FILE);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
  }
}
