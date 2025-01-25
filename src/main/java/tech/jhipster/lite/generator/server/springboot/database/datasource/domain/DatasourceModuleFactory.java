package tech.jhipster.lite.generator.server.springboot.database.datasource.domain;

import static tech.jhipster.lite.generator.server.springboot.database.datasource.domain.DatabaseType.MARIADB;
import static tech.jhipster.lite.generator.server.springboot.database.datasource.domain.DatabaseType.MSSQL;
import static tech.jhipster.lite.generator.server.springboot.database.datasource.domain.DatabaseType.MYSQL;
import static tech.jhipster.lite.generator.server.springboot.database.datasource.domain.DatabaseType.POSTGRESQL;
import static tech.jhipster.lite.module.domain.JHipsterModule.artifactId;
import static tech.jhipster.lite.module.domain.JHipsterModule.documentationTitle;
import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.groupId;
import static tech.jhipster.lite.module.domain.JHipsterModule.lineBeforeText;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.path;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyKey;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyValue;
import static tech.jhipster.lite.module.domain.JHipsterModule.to;
import static tech.jhipster.lite.module.domain.JHipsterModule.toSrcMainDocker;
import static tech.jhipster.lite.module.domain.JHipsterModule.toSrcTestJava;

import java.util.function.Consumer;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.LogLevel;
import tech.jhipster.lite.module.domain.docker.DockerImageVersion;
import tech.jhipster.lite.module.domain.docker.DockerImages;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class DatasourceModuleFactory {

  private static final String PROPERTIES = "properties";
  private static final String ORG_POSTGRESQL = "org.postgresql";

  private static final String SPRING_DATASOURCE_URL = "spring.datasource.url";
  private static final String SPRING_DATASOURCE_USERNAME = "spring.datasource.username";
  private static final String SPRING_DATASOURCE_PASSWORD = "spring.datasource.password";
  private static final String SPRING_DATASOURCE_DRIVER_CLASS_NAME = "spring.datasource.driver-class-name";
  private static final JHipsterSource COMMON_SOURCE = from("server/springboot/database/common");

  private final DockerImages dockerImages;

  public DatasourceModuleFactory(DockerImages dockerImages) {
    this.dockerImages = dockerImages;
  }

  public JHipsterModule buildPostgresql(JHipsterModuleProperties properties) {
    Assert.notNull(PROPERTIES, properties);

    DockerImageVersion dockerImage = dockerImages.get(POSTGRESQL.dockerImageName());

    //@formatter:off
    return moduleBuilder(properties)
      .apply(dockerContainer(POSTGRESQL))
      .apply(connectionPool(POSTGRESQL))
      .apply(testcontainers(POSTGRESQL, properties))
      .springMainProperties()
        .set(propertyKey(SPRING_DATASOURCE_URL), propertyValue("jdbc:postgresql://localhost:5432/" + properties.projectBaseName().name()))
        .set(propertyKey(SPRING_DATASOURCE_USERNAME), propertyValue(properties.projectBaseName().name()))
        .and()
      .springTestProperties()
        .set(
          propertyKey(SPRING_DATASOURCE_URL),
          propertyValue(
            "jdbc:tc:postgresql:" + dockerImage.version().get() + ":///" + properties.projectBaseName().name() + "?TC_TMPFS=/testtmpfs:rw"
          )
        )
        .and()
      .springMainLogger(ORG_POSTGRESQL, LogLevel.WARN)
      .springTestLogger(ORG_POSTGRESQL, LogLevel.WARN)
      .springTestLogger("org.jboss.logging", LogLevel.WARN)
      .build();
    //@formatter:on
  }

  public JHipsterModule buildMariaDB(JHipsterModuleProperties properties) {
    Assert.notNull(PROPERTIES, properties);

    //@formatter:off
    return moduleBuilder(properties)
      .apply(dockerContainer(MARIADB))
      .apply(connectionPool(MARIADB))
      .apply(testcontainers(MARIADB, properties))
      .springMainProperties()
        .set(propertyKey(SPRING_DATASOURCE_URL), propertyValue("jdbc:mariadb://localhost:3306/" + properties.projectBaseName().name()))
        .set(propertyKey(SPRING_DATASOURCE_USERNAME), propertyValue("root"))
        .and()
      .build();
    //@formatter:on
  }

  public JHipsterModule buildMySQL(JHipsterModuleProperties properties) {
    Assert.notNull(PROPERTIES, properties);

    //@formatter:off
    return moduleBuilder(properties)
      .apply(dockerContainer(MYSQL))
      .apply(connectionPool(MYSQL))
      .apply(testcontainers(MYSQL, properties))
      .springMainProperties()
        .set(propertyKey(SPRING_DATASOURCE_URL), propertyValue("jdbc:mysql://localhost:3306/" + properties.projectBaseName().name()))
        .set(propertyKey(SPRING_DATASOURCE_USERNAME), propertyValue("root"))
        .and()
      .build();
    //@formatter:on
  }

  public JHipsterModule buildMsSQL(JHipsterModuleProperties properties) {
    Assert.notNull(PROPERTIES, properties);

    JHipsterSource source = from("server/springboot/database/common");

    //@formatter:off
    return moduleBuilder(properties)
      .apply(dockerContainer(MSSQL))
      .apply(connectionPool(MSSQL))
      .apply(testcontainers(MSSQL, properties))
      .files()
        .add(source.append("docker").template("container-license-acceptance.txt"), to("src/test/resources/container-license-acceptance.txt"))
        .add(
          source.template("MsSQLTestContainerExtension.java"),
          toSrcTestJava().append(properties.basePackage().path()).append("MsSQLTestContainerExtension.java")
        )
        .and()
      .springMainProperties()
        .set(
          propertyKey(SPRING_DATASOURCE_URL),
          propertyValue("jdbc:sqlserver://localhost:1433;database=" + properties.projectBaseName().name() + ";trustServerCertificate=true")
        )
        .set(propertyKey(SPRING_DATASOURCE_USERNAME), propertyValue("SA"))
        .set(propertyKey(SPRING_DATASOURCE_PASSWORD), propertyValue("yourStrong(!)Password"))
        .and()
      .springTestProperties()
        .set(
          propertyKey(SPRING_DATASOURCE_URL),
          propertyValue(
            "jdbc:tc:sqlserver:///;database=" + properties.projectBaseName().name() + ";trustServerCertificate=true?TC_TMPFS=/testtmpfs:rw"
          )
        )
        .set(propertyKey(SPRING_DATASOURCE_USERNAME), propertyValue("SA"))
        .set(propertyKey(SPRING_DATASOURCE_PASSWORD), propertyValue("yourStrong(!)Password"))
        .and()
      .mandatoryReplacements()
        .in(path("src/test/java").append(properties.basePackage().path()).append("IntegrationTest.java"))
          .add(
            lineBeforeText("import org.springframework.boot.test.context.SpringBootTest;"),
            "import org.junit.jupiter.api.extension.ExtendWith;"
          )
          .add(lineBeforeText("public @interface"), "@ExtendWith(MsSQLTestContainerExtension.class)")
          .and()
        .and()
      .springMainLogger("com.microsoft.sqlserver", LogLevel.WARN)
      .springMainLogger("org.reflections", LogLevel.WARN)
      .build();
    //@formatter:on
  }

  private Consumer<JHipsterModule.JHipsterModuleBuilder> dockerContainer(DatabaseType databaseType) {
    DockerImageVersion dockerImage = dockerImages.get(databaseType.dockerImageName());

    //@formatter:off
    return moduleBuilder ->
      moduleBuilder
        .context()
          .put("srcMainDocker", "src/main/docker")
          .put("databaseType", databaseType.id())
          .put(databaseType.id() + "DockerImageWithVersion", dockerImage.fullName())
          .and()
        .documentation(documentationTitle(databaseType.databaseName()), COMMON_SOURCE.template("databaseType.md"))
        .startupCommands()
          .dockerCompose("src/main/docker/" + databaseType.id() + ".yml")
          .and()
        .files()
          .add(COMMON_SOURCE.append("docker").template(databaseType.id() + ".yml"), toSrcMainDocker().append(databaseType.id() + ".yml"));
  }

  private Consumer<JHipsterModule.JHipsterModuleBuilder> testcontainers(DatabaseType databaseType, JHipsterModuleProperties properties) {
    DockerImageVersion dockerImage = dockerImages.get(databaseType.dockerImageName());

    //@formatter:off
    return moduleBuilder ->
      moduleBuilder
        .javaDependencies()
        .addDependency(databaseType.testContainerDependency())
        .and()
        .springTestProperties()
        .set(
          propertyKey(SPRING_DATASOURCE_URL),
          propertyValue("jdbc:tc:" + dockerImage.fullName() + ":///" + properties.projectBaseName().name())
        )
        .set(propertyKey(SPRING_DATASOURCE_USERNAME), propertyValue(properties.projectBaseName().name()))
        .set(propertyKey(SPRING_DATASOURCE_PASSWORD), propertyValue(""))
        .set(propertyKey(SPRING_DATASOURCE_DRIVER_CLASS_NAME), propertyValue("org.testcontainers.jdbc.ContainerDatabaseDriver"))
        .and()
        .springTestLogger("com.github.dockerjava", LogLevel.WARN)
        .springTestLogger("org.testcontainers", LogLevel.WARN);
  }

  private Consumer<JHipsterModule.JHipsterModuleBuilder> connectionPool(DatabaseType databaseType) {
    //@formatter:off
    return moduleBuilder ->
      moduleBuilder
        .javaDependencies()
          .addDependency(databaseType.driverDependency())
          .addDependency(groupId("com.zaxxer"), artifactId("HikariCP"))
          .and()
        .springMainProperties()
          .set(propertyKey(SPRING_DATASOURCE_PASSWORD), propertyValue(""))
          .set(propertyKey("spring.datasource.type"), propertyValue("com.zaxxer.hikari.HikariDataSource"))
          .set(propertyKey("spring.datasource.hikari.poolName"), propertyValue("Hikari"))
          .set(propertyKey("spring.datasource.hikari.auto-commit"), propertyValue(false))
          .set(propertyKey(SPRING_DATASOURCE_DRIVER_CLASS_NAME), propertyValue(databaseType.driverClassName()))
          .and()
        .springTestProperties()
          .set(propertyKey("spring.datasource.hikari.maximum-pool-size"), propertyValue(2));
  }
}
