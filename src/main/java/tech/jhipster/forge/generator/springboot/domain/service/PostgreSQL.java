package tech.jhipster.forge.generator.springboot.domain.service;

public class PostgreSQL {

  public static final String TESTCONTAINERS_VERSION = "1.16.0";

  private PostgreSQL() {}

  public static String getTestcontainersVersion() {
    return TESTCONTAINERS_VERSION;
  }
}
