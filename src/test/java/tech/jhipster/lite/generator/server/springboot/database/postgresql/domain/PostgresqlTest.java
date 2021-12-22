package tech.jhipster.lite.generator.server.springboot.database.postgresql.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

@UnitTest
class PostgresqlTest {

  @Test
  void shouldGetTestcontainersVersion() {
    assertThat(Postgresql.getTestcontainersVersion()).isEqualTo(Postgresql.TESTCONTAINERS_VERSION);
  }

  @Test
  void shouldGetPostgresqlDockerVersion() {
    assertThat(Postgresql.getPostgresqlDockerVersion()).isEqualTo(Postgresql.POSTGRESQL_DOCKER_VERSION);
  }

  @Test
  void shouldGetPostgresqlDockerImage() {
    assertThat(Postgresql.getPostgresqlDockerImage()).isEqualTo(Postgresql.POSTGRESQL_DOCKER_IMAGE);
  }

  @Test
  void shouldPsqlConnectorJava() {
    Dependency dependency = Postgresql.psqlDriver();

    assertThat(dependency.getGroupId()).isEqualTo("org.postgresql");
    assertThat(dependency.getArtifactId()).isEqualTo("postgresql");
  }

  @Test
  void shouldPsqlHikari() {
    Dependency dependency = Postgresql.psqlHikari();

    assertThat(dependency.getGroupId()).isEqualTo("com.zaxxer");
    assertThat(dependency.getArtifactId()).isEqualTo("HikariCP");
  }

  @Test
  void shouldPsqlHibernateCore() {
    Dependency dependency = Postgresql.psqlHibernateCore();

    assertThat(dependency.getGroupId()).isEqualTo("org.hibernate");
    assertThat(dependency.getArtifactId()).isEqualTo("hibernate-core");
  }
}
