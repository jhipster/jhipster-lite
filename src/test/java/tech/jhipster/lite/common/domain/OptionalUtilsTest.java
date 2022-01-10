package tech.jhipster.lite.common.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class OptionalUtilsTest {

  @Test
  void shouldNotBlankForNull() {
    assertThat(OptionalUtils.notBlank(null)).isEmpty();
  }

  @Test
  void shouldNotBlankForEmpty() {
    assertThat(OptionalUtils.notBlank(" ")).isEmpty();
  }

  @Test
  void shouldNotBlank() {
    assertThat(OptionalUtils.notBlank("chips")).isEqualTo(Optional.of("chips"));
  }
}
