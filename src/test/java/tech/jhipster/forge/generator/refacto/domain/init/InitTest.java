package tech.jhipster.forge.generator.refacto.domain.init;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.generator.init.domain.Init;

@UnitTest
class InitTest {

  @Test
  void shouldGetNodeVersion() {
    assertThat(Init.getNodeVersion()).isEqualTo(Init.NODE_VERSION);
  }
}
