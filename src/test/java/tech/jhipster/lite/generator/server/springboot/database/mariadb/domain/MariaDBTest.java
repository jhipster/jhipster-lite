package tech.jhipster.lite.generator.server.springboot.database.mariadb.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.server.springboot.database.mysql.domain.MySQL;

@UnitTest
class MariaDBTest {

  @Test
  void shouldGetDockerImageName() {
    assertThat(MariaDB.getDockerImageName()).isEqualTo("mariadb:10.8.2");
  }

  @Test
  void shouldGetMariaDBConnectorJava() {
    Dependency dependency = MariaDB.mariadbConnectorJava();

    assertThat(dependency.getGroupId()).isEqualTo("org.mariadb.jdbc");
    assertThat(dependency.getArtifactId()).isEqualTo("mariadb-java-client");
  }

  @Test
  void shouldGetSpringProperties() {
    Map<String, Object> springProperties = MariaDB.springProperties("baseName");

    assertThat(springProperties)
      .containsEntry("spring.datasource.url", "jdbc:mariadb://localhost:3306/baseName")
      .containsEntry("spring.datasource.username", "root")
      .containsEntry("spring.datasource.driver-class-name", "org.mariadb.jdbc.Driver");
  }
}
