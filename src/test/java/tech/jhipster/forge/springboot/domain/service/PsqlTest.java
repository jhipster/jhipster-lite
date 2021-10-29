package tech.jhipster.forge.springboot.domain.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;

@UnitTest
class PsqlTest {

  @Test
  void shouldGetTestcontainersVersion() {
    assertThat(Psql.getTestcontainersVersion()).isEqualTo("1.16.0");
  }
}
