package tech.jhipster.forge.springboot.domain.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;

@UnitTest
class InitTest {

  @Test
  void shouldGetNodeVersion() {
    assertThat(Init.getNodeVersion()).isEqualTo(Init.NODE_VERSION);
  }
}
