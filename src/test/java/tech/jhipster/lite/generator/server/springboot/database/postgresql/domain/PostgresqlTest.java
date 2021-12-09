package tech.jhipster.lite.generator.server.springboot.database.postgresql.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class PostgresqlTest {

  @Test
  void shouldGetTestcontainersVersion() {
    assertThat(Postgresql.getTestcontainersVersion()).isEqualTo("1.16.0");
  }
}
