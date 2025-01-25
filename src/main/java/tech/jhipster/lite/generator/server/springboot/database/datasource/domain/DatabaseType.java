package tech.jhipster.lite.generator.server.springboot.database.datasource.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.artifactId;
import static tech.jhipster.lite.module.domain.JHipsterModule.javaDependency;
import static tech.jhipster.lite.module.domain.javadependency.JavaDependencyScope.RUNTIME;

import tech.jhipster.lite.module.domain.docker.DockerImageName;
import tech.jhipster.lite.module.domain.javabuild.ArtifactId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependency;

@SuppressWarnings("java:S1192")
enum DatabaseType {
  POSTGRESQL(
    "postgresql",
    "PostgreSQL",
    new DockerImageName("postgres"),
    javaDependency().groupId("org.postgresql").artifactId("postgresql").scope(RUNTIME).build(),
    "org.postgresql.Driver",
    artifactId("postgres")
  ),
  MARIADB(
    "mariadb",
    "MariaDB",
    new DockerImageName("mariadb"),
    javaDependency().groupId("org.mariadb.jdbc").artifactId("mariadb-java-client").scope(RUNTIME).build(),
    "org.mariadb.jdbc.Driver",
    artifactId("mariadb")
  ),
  MYSQL(
    "mysql",
    "MySQL",
    new DockerImageName("mysql"),
    javaDependency().groupId("com.mysql").artifactId("mysql-connector-j").scope(RUNTIME).build(),
    "com.mysql.cj.jdbc.Driver",
    artifactId("mysql")
  ),
  MSSQL(
    "mssql",
    "MsSQL",
    new DockerImageName("mcr.microsoft.com/mssql/server"),
    javaDependency().groupId("com.microsoft.sqlserver").artifactId("mssql-jdbc").scope(RUNTIME).build(),
    "com.microsoft.sqlserver.jdbc.SQLServerDriver",
    artifactId("mssqlserver")
  );

  private final DatasourceProperties properties;

  DatabaseType(DatasourceProperties properties) {
    this.properties = properties;
  }

  DatabaseType(
    String id,
    String databaseName,
    DockerImageName dockerImageName,
    JavaDependency driverDependency,
    String driveClassName,
    ArtifactId testContainerArtifactId
  ) {
    this.properties = DatasourceProperties.builder()
      .id(id)
      .databaseName(databaseName)
      .driverDependency(driverDependency)
      .driverClassName(driveClassName)
      .dockerImageName(dockerImageName)
      .testContainerArtifactId(testContainerArtifactId);
  }

  public String id() {
    return properties.id();
  }

  public String databaseName() {
    return properties.databaseName();
  }

  public DockerImageName dockerImageName() {
    return properties.dockerImageName();
  }

  public JavaDependency driverDependency() {
    return properties.driverDependency();
  }

  public JavaDependency testContainerDependency() {
    return properties.testContainerDependency();
  }

  public String driverClassName() {
    return properties.driverClassName();
  }
}
