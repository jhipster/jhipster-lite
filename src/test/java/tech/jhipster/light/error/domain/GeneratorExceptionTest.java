package tech.jhipster.light.error.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.light.UnitTest;

@UnitTest
class GeneratorExceptionTest {

  @Test
  void shouldGetGeneratorException() {
    GeneratorException exception = new GeneratorException("apero");
    assertThat(exception.getMessage()).isEqualTo("apero");
  }

  @Test
  void shouldGetGeneratorExceptionWithCause() {
    NullPointerException nullPointerException = new NullPointerException();
    GeneratorException exception = new GeneratorException("apero", nullPointerException);
    assertThat(exception.getMessage()).isEqualTo("apero");
  }
}
