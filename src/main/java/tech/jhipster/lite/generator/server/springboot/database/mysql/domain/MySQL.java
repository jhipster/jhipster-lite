package tech.jhipster.lite.generator.server.springboot.database.mysql.domain;

public class MySQL {

  public static final String TESTCONTAINERS_VERSION = "1.16.0";

  private MySQL() {}

  public static String getTestcontainersVersion() {
    return TESTCONTAINERS_VERSION;
  }
}
