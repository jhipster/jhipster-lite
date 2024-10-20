package tech.jhipster.lite.module.domain.jooqplugin;

//TODO shared enum

public enum DatabaseType {
  POSTGRESQL("postgresql"),
  MYSQL("mysql"),
  MARIADB("mariadb"),
  MSSQL("mssql");

  private final String id;

  DatabaseType(String id) {
    this.id = id;
  }

  public String id() {
    return id;
  }
}
