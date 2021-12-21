package tech.jhipster.lite.generator.server.springboot.database.mysql.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

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

  @Test
  void shouldMysqlConnectorJava() {
    Dependency dependency = MySQL.mysqlConnectorJava();

    assertThat(dependency.getGroupId()).isEqualTo("mysql");
    assertThat(dependency.getArtifactId()).isEqualTo("mysql-connector-java");
  }

  @Test
  void shouldMysqlHikari() {
    Dependency dependency = MySQL.mysqlHikari();

    assertThat(dependency.getGroupId()).isEqualTo("com.zaxxer");
    assertThat(dependency.getArtifactId()).isEqualTo("HikariCP");
  }

  @Test
  void shouldMysqlHibernateCore() {
    Dependency dependency = MySQL.mysqlHibernateCore();

    assertThat(dependency.getGroupId()).isEqualTo("org.hibernate");
    assertThat(dependency.getArtifactId()).isEqualTo("hibernate-core");
  }
}
