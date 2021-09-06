package tech.jhipster.forge.error.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;

@UnitTest
class GeneratorExceptionTest {

  @Test
  void shouldGetGeneratorException() {
    GeneratorException exception = new GeneratorException("apero");
    assertThat(exception.getMessage()).isEqualTo("apero");
  }
}
