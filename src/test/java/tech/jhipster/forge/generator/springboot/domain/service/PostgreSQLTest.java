package tech.jhipster.forge.generator.springboot.domain.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;

@UnitTest
class PostgreSQLTest {

  @Test
  void shouldGetTestcontainersVersion() {
    assertThat(PostgreSQL.getTestcontainersVersion()).isEqualTo("1.16.0");
  }
}
