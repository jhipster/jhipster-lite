package tech.jhipster.lite.generator.server.springboot.core.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class SpringBootTest {

  @Test
  void shouldGetVersion() {
    Assertions.assertThat(SpringBoot.getVersion()).isEqualTo(SpringBoot.SPRING_BOOT_VERSION);
  }
}
