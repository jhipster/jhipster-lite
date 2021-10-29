package tech.jhipster.forge.generator.domain.server.springboot.database.psql;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.generator.domain.server.springboot.database.psql.Psql;

@UnitTest
class PsqlTest {

  @Test
  void shouldGetTestcontainersVersion() {
    assertThat(Psql.getTestcontainersVersion()).isEqualTo("1.16.0");
  }
}
