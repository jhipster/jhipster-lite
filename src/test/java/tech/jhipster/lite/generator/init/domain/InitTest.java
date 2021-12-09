package tech.jhipster.lite.generator.init.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class InitTest {

  @Test
  void shouldGetNodeVersion() {
    assertThat(Init.getNodeVersion()).isEqualTo(Init.NODE_VERSION);
  }
}
