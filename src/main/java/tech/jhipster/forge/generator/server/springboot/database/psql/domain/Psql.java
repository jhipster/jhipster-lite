package tech.jhipster.forge.generator.server.springboot.database.psql.domain;

public class Psql {

  public static final String TESTCONTAINERS_VERSION = "1.16.0";

  private Psql() {}

  public static String getTestcontainersVersion() {
    return TESTCONTAINERS_VERSION;
  }
}
