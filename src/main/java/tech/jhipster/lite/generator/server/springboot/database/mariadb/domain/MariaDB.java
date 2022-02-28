package tech.jhipster.lite.generator.server.springboot.database.mariadb.domain;

import java.util.Map;
import java.util.TreeMap;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class MariaDB {

  public static final String DOCKER_IMAGE_NAME = "mariadb:10.8.2";
  public static final String SOURCE = "server/springboot/database/mariadb";

  private MariaDB() {}

  public static String getDockerImageName() {
    return DOCKER_IMAGE_NAME;
  }

  public static Dependency mariadbConnectorJava() {
    return Dependency.builder().groupId("org.mariadb.jdbc").artifactId("mariadb-java-client").build();
  }

  public static Map<String, Object> springProperties(String baseName) {
    TreeMap<String, Object> result = new TreeMap<>();
    result.put("spring.datasource.url", "jdbc:mariadb://localhost:3306/" + baseName);
    result.put("spring.datasource.username", "root");
    result.put("spring.datasource.driver-class-name", "org.mariadb.jdbc.Driver");
    return result;
  }

  public static Map<String, Object> springPropertiesForTest(String baseName) {
    TreeMap<String, Object> result = new TreeMap<>();
    result.put("spring.datasource.driver-class-name", "org.testcontainers.jdbc.ContainerDatabaseDriver");
    result.put("spring.datasource.url", "jdbc:tc:" + MariaDB.getDockerImageName() + ":///" + baseName);
    result.put("spring.datasource.username", baseName);
    result.put("spring.datasource.password", "");
    result.put("spring.datasource.hikari.maximum-pool-size", 2);
    return result;
  }
}
