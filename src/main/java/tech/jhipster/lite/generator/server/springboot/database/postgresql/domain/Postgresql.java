package tech.jhipster.lite.generator.server.springboot.database.postgresql.domain;

public class Postgresql {

  public static final String TESTCONTAINERS_VERSION = "1.16.0";

  private Postgresql() {}

  public static String getTestcontainersVersion() {
    return TESTCONTAINERS_VERSION;
  }
}
