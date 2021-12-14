package tech.jhipster.lite.generator.server.springboot.database.mysql.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class MySQLTest {

  @Test
  void shouldGetTestcontainersVersion() {
    assertThat(MySQL.getTestcontainersVersion()).isEqualTo("1.16.0");
  }

  @Test
  void shouldGetDockerImageName() {
    assertThat(MySQL.getDockerImageName()).isEqualTo("mysql:8.0.27");
  }
}
