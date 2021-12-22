package tech.jhipster.lite.generator.server.springboot.database.mysql.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class MySQL {

  public static final String TESTCONTAINERS_VERSION = "1.16.2";

  public static final String DOCKER_IMAGE_NAME = "mysql:8.0.27";

  private MySQL() {}

  public static String getTestcontainersVersion() {
    return TESTCONTAINERS_VERSION;
  }

  public static String getDockerImageName() {
    return DOCKER_IMAGE_NAME;
  }

  public static Dependency mysqlConnectorJava() {
    return Dependency.builder().groupId("mysql").artifactId("mysql-connector-java").build();
  }

  public static Dependency mysqlHikari() {
    return Dependency.builder().groupId("com.zaxxer").artifactId("HikariCP").build();
  }

  public static Dependency mysqlHibernateCore() {
    return Dependency.builder().groupId("org.hibernate").artifactId("hibernate-core").build();
  }
}
