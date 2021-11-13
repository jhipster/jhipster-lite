package tech.jhipster.light.generator.server.springboot.core.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.jhipster.light.UnitTest;

@UnitTest
class SpringBootTest {

  @Test
  void shouldGetVersion() {
    Assertions.assertThat(SpringBoot.getVersion()).isEqualTo(SpringBoot.SPRING_BOOT_VERSION);
  }
}
