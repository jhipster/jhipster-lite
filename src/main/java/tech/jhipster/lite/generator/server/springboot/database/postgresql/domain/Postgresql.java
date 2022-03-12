package tech.jhipster.lite.generator.server.springboot.database.postgresql.domain;

import java.util.Map;
import java.util.TreeMap;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class Postgresql {

  public static final String SOURCE = "server/springboot/database/postgresql";

  public static final String POSTGRESQL_DOCKER_IMAGE_NAME = "postgres";

  private Postgresql() {}

  public static String getPostgresqlDockerImageName() {
    return POSTGRESQL_DOCKER_IMAGE_NAME;
  }

  public static Dependency psqlDriver() {
    return Dependency.builder().groupId("org.postgresql").artifactId("postgresql").build();
  }

  public static Dependency psqlHikari() {
    return Dependency.builder().groupId("com.zaxxer").artifactId("HikariCP").build();
  }

  public static Dependency psqlHibernateCore() {
    return Dependency.builder().groupId("org.hibernate").artifactId("hibernate-core").build();
  }

  public static Map<String, Object> springProperties(String baseName, String packageName) {
    TreeMap<String, Object> result = new TreeMap<>();

    result.put("spring.datasource.url", "jdbc:postgresql://localhost:5432/" + baseName);
    result.put("spring.datasource.username", baseName);
    result.put("spring.datasource.password", "");
    result.put("spring.datasource.driver-class-name", "org.postgresql.Driver");
    result.put("spring.jpa.database-platform", packageName + ".technical.infrastructure.secondary.postgresql.FixedPostgreSQL10Dialect");

    return result;
  }

  public static Map<String, Object> springPropertiesForTest(String baseName, String postgresqlVersion) {
    TreeMap<String, Object> result = new TreeMap<>();
    result.put("spring.datasource.driver-class-name", "org.testcontainers.jdbc.ContainerDatabaseDriver");
    result.put("spring.datasource.url", "jdbc:tc:postgresql:" + postgresqlVersion + ":///" + baseName + "?TC_TMPFS=/testtmpfs:rw");
    result.put("spring.datasource.username", baseName);
    result.put("spring.datasource.password", "");
    result.put("spring.datasource.hikari.maximum-pool-size", 2);
    return result;
  }
}
