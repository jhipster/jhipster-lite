package tech.jhipster.lite.module.domain.jooqplugin;

import tech.jhipster.lite.shared.error.domain.Assert;

public final class JooqModuleCodegenConfiguration {

  private final DatabaseType database;
  private final String databaseUrl;
  private final String user;
  private final String password;
  private final String inputSchema;

  private JooqModuleCodegenConfiguration(JooqModuleCodegenConfigurationBuilder builder) {
    Assert.notNull("database", builder.database);
    Assert.notNull("databaseUrl", builder.databaseUrl);
    Assert.notNull("user", builder.user);
    Assert.notNull("password", builder.password);
    Assert.notNull("inputSchema", builder.inputSchema);

    this.database = builder.database;
    this.databaseUrl = builder.databaseUrl;
    this.user = builder.user;
    this.inputSchema = builder.inputSchema;
    this.password = builder.password; //TODO password should be optional in the builder with default value ""
  }

  public String buildConfiguration() {
    return String.format(
      """
      <jdbc>
        <driver>%s</driver>
        <url>%s</url>
        <user>%s</user>
        <password>%s</password>
      </jdbc>
      <generator>
        <database>
          <name>%s</name>
          <includes>.*</includes>
          <inputSchema>%s</inputSchema>
        </database>
        <target>
          <packageName>org.jooq.codegen</packageName>
          <directory>target/generated-sources/jooq</directory>
        </target>
      </generator>
      """,
      databaseJooqName(),
      databaseUrl,
      user,
      password,
      databaseJooqName(),
      inputSchema
    );
  }

  private String databaseJooqName() {
    return switch (database) {
      case POSTGRESQL -> "org.jooq.meta.postgres.PostgresDatabase";
      case MYSQL -> "org.jooq.meta.mysql.MySQLDatabase";
      case MARIADB -> "org.jooq.meta.mariadb.MariaDBDatabase";
      case MSSQL -> "org.jooq.meta.sqlserver.SQLServerDatabase";
    };
  }

  private String databaseDriver() {
    return switch (database) {
      case POSTGRESQL -> "org.postgresql.Driver";
      case MARIADB -> "org.mariadb.jdbc.Driver";
      case MYSQL -> "com.mysql.jdbc.Driver";
      case MSSQL -> "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    };
  }

  public static JooqModuleCodegenConfigurationBuilder builder() {
    return new JooqModuleCodegenConfigurationBuilder();
  }

  public interface JooqModuleCodegenConfigurationDatabaseBuilder {
    JooqModuleCodegenConfigurationBuilder database(DatabaseType database);
  }

  public interface JooqModuleCodegenConfigurationDatabaseUrlBuilder {
    JooqModuleCodegenConfigurationBuilder databaseUrl(String databaseUrl);
  }

  public interface JooqModuleCodegenConfigurationUserBuilder {
    JooqModuleCodegenConfigurationBuilder user(String user);
  }

  public interface JooqModuleCodegenConfigurationPasswordBuilder {
    JooqModuleCodegenConfigurationBuilder password(String password);
  }

  public interface JooqModuleCodegenConfigurationInputSchemaBuilder {
    JooqModuleCodegenConfiguration inputSchema(String inputSchema);
  }

  public static final class JooqModuleCodegenConfigurationBuilder
    implements
      JooqModuleCodegenConfigurationDatabaseBuilder,
      JooqModuleCodegenConfigurationDatabaseUrlBuilder,
      JooqModuleCodegenConfigurationUserBuilder,
      JooqModuleCodegenConfigurationPasswordBuilder,
      JooqModuleCodegenConfigurationInputSchemaBuilder {

    private DatabaseType database;
    private String databaseUrl;
    private String user;
    private String password;
    private String inputSchema;

    @Override
    public JooqModuleCodegenConfigurationBuilder databaseUrl(String databaseUrl) {
      this.databaseUrl = databaseUrl;

      return this;
    }

    @Override
    public JooqModuleCodegenConfigurationBuilder database(DatabaseType database) {
      this.database = database;

      return this;
    }

    @Override
    public JooqModuleCodegenConfigurationBuilder password(String password) {
      this.password = password;

      return this;
    }

    @Override
    public JooqModuleCodegenConfigurationBuilder user(String user) {
      this.user = user;

      return this;
    }

    @Override
    public JooqModuleCodegenConfiguration inputSchema(String inputSchema) {
      this.inputSchema = inputSchema;

      return new JooqModuleCodegenConfiguration(this);
    }
  }
}
