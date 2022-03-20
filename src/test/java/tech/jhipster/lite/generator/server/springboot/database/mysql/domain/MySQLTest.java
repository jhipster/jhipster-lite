package tech.jhipster.lite.generator.server.springboot.database.mysql.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

@UnitTest
class MySQLTest {

  @Test
  void shouldGetDockerImageName() {
    assertThat(MySQL.getDockerImageName()).isEqualTo("mysql");
  }

  @Test
  void shouldMysqlConnectorJava() {
    Dependency dependency = MySQL.mysqlConnectorJava();

    assertThat(dependency.getGroupId()).isEqualTo("mysql");
    assertThat(dependency.getArtifactId()).isEqualTo("mysql-connector-java");
  }
}
