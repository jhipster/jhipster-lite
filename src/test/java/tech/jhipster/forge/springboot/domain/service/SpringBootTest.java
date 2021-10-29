package tech.jhipster.forge.springboot.domain.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;

@UnitTest
class SpringBootTest {

  @Test
  void shouldGetVersion() {
    Assertions.assertThat(SpringBoot.getVersion()).isEqualTo(SpringBoot.SPRING_BOOT_VERSION);
  }
}
