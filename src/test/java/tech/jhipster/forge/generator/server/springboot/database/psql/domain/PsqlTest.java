package tech.jhipster.forge.generator.server.springboot.database.psql.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;

@UnitTest
class PsqlTest {

  @Test
  void shouldGetTestcontainersVersion() {
    assertThat(Psql.getTestcontainersVersion()).isEqualTo("1.16.0");
  }
}
