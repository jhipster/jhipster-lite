package tech.jhipster.lite.generator.server.springboot.database.postgresql.domain;

import static tech.jhipster.lite.common.domain.WordUtils.LF;
import static tech.jhipster.lite.generator.module.domain.JHipsterModule.*;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.NEEDLE_LOGBACK_LOGGER;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.docker.domain.DockerImage;
import tech.jhipster.lite.generator.docker.domain.DockerImages;
import tech.jhipster.lite.generator.module.domain.JHipsterDestination;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.JHipsterSource;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependencyScope;
import tech.jhipster.lite.generator.module.domain.javaproperties.PropertyKey;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.project.domain.DatabaseType;
import tech.jhipster.lite.generator.server.springboot.common.domain.Level;

public class PostgresqlModuleFactory {

  private static final String SOURCE = "server/springboot/database/postgresql";
  private static final String DEST_SECONDARY = "technical/infrastructure/secondary/postgresql";
  public static final String POSTGRESQL_DOCKER_IMAGE_NAME = "postgres";
  public static final String SRC_MAIN_DOCKER = "src/main/docker";
  public static final String MAIN_RESOURCES = "src/main/resources";
  public static final String LOGGING_TEST_CONFIGURATION = "logback.xml";
  public static final String ORG_POSTGRESQL = "org.postgresql";
  public static final String ORG_HIBERNATE = "org.hibernate";
  public static final String FALSE = "false";
  public static final String TRUE = "true";

  private final DockerImages dockerImages;

  public PostgresqlModuleFactory(DockerImages dockerImages) {
    this.dockerImages = dockerImages;
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);
    DockerImage postgresqlDockerImage = dockerImages.get(POSTGRESQL_DOCKER_IMAGE_NAME);

    JHipsterModuleBuilder builder = moduleBuilder(properties);
    appendContext(builder, properties, postgresqlDockerImage);
    appendDocumentation(builder);
    appendFiles(builder, properties);
    appendJavaDependencies(builder);
    appendSpringProperties(builder, properties, postgresqlDockerImage);
    appendReplacements(builder);

    return builder.build();
  }

  private void appendContext(JHipsterModuleBuilder builder, JHipsterModuleProperties properties, DockerImage dockerImage) {
    builder
      .context()
      .packageName(properties.basePackage())
      .put("applicationName", properties.projectBaseName().capitalized())
      .put("srcDocker", SRC_MAIN_DOCKER)
      .put("postgresqlDockerImageWithVersion", dockerImage.fullName());
  }

  private void appendDocumentation(JHipsterModuleBuilder builder) {
    builder.documentation(documentationTitle("Postgresql"), from("server/springboot/database/postgresql/postgresql.md.mustache"));
  }

  private void appendFiles(JHipsterModuleBuilder builder, JHipsterModuleProperties properties) {
    JHipsterSource source = from(SOURCE);
    JHipsterDestination databasePath = toSrcMainJava().append(properties.basePackage().path()).append(DEST_SECONDARY);
    builder
      .files()
      .add(source.template("DatabaseConfiguration.java"), databasePath.append("DatabaseConfiguration.java"))
      .add(source.template("FixedPostgreSQL10Dialect.java"), databasePath.append("FixedPostgreSQL10Dialect.java"))
      .add(
        source.template("FixedPostgreSQL10DialectTest.java"),
        toSrcTestJava().append(properties.basePackage().path()).append(DEST_SECONDARY).append("FixedPostgreSQL10DialectTest.java")
      )
      .add(source.template("postgresql.yml"), toSrcMainDocker().append("postgresql.yml"));
  }

  private void appendJavaDependencies(JHipsterModuleBuilder builder) {
    builder.javaDependencies().add(springDataJpa()).add(psqlDriver()).add(psqlHikari()).add(psqlHibernateCore()).add(testContainer());
  }

  private void appendSpringProperties(JHipsterModuleBuilder builder, JHipsterModuleProperties properties, DockerImage dockerImage) {
    builder
      .springMainProperties()
      .set(springDatasourceUrl(), propertyValue("jdbc:postgresql://localhost:5432/" + properties.projectBaseName().name()))
      .set(springDatasourceUsername(), propertyValue(properties.projectBaseName().name()))
      .set(springDatasourcePassword(), propertyValue(""))
      .set(springDatasourceDriverClassName(), propertyValue("org.postgresql.Driver"))
      .set(
        propertyKey("spring.jpa.database-platform"),
        propertyValue(properties.basePackage().basePackage() + ".technical.infrastructure.secondary.postgresql.FixedPostgreSQL10Dialect")
      )
      .set(propertyKey("spring.datasource.type"), propertyValue("com.zaxxer.hikari.HikariDataSource"))
      .set(propertyKey("spring.datasource.hikari.poolName"), propertyValue("Hikari"))
      .set(propertyKey("spring.datasource.hikari.auto-commit"), propertyValue(FALSE))
      .set(propertyKey("spring.data.jpa.repositories.bootstrap-mode"), propertyValue("deferred"))
      .set(propertyKey("spring.jpa.properties.hibernate.jdbc.time_zone"), propertyValue("UTC"))
      .set(propertyKey("spring.jpa.open-in-view"), propertyValue(FALSE))
      .set(propertyKey("spring.jpa.properties.hibernate.id.new_generator_mappings"), propertyValue(TRUE))
      .set(propertyKey("spring.jpa.properties.hibernate.connection.provider_disables_autocommit"), propertyValue(TRUE))
      .set(propertyKey("spring.jpa.properties.hibernate.generate_statistics"), propertyValue(FALSE))
      .set(propertyKey("spring.jpa.properties.hibernate.jdbc.batch_size"), propertyValue("25"))
      .set(propertyKey("spring.jpa.properties.hibernate.order_inserts"), propertyValue(TRUE))
      .set(propertyKey("spring.jpa.properties.hibernate.order_updates"), propertyValue(TRUE))
      .set(propertyKey("spring.jpa.properties.hibernate.query.fail_on_pagination_over_collection_fetch"), propertyValue(TRUE))
      .set(propertyKey("spring.jpa.properties.hibernate.query.in_clause_parameter_padding"), propertyValue(TRUE))
      .set(propertyKey("spring.jpa.hibernate.ddl-auto"), propertyValue("none"))
      .set(
        propertyKey("spring.jpa.hibernate.naming.physical-strategy"),
        propertyValue("org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy")
      )
      .set(
        propertyKey("spring.jpa.hibernate.naming.implicit-strategy"),
        propertyValue("org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy")
      );

    builder
      .springTestProperties()
      .set(springDatasourceDriverClassName(), propertyValue("org.testcontainers.jdbc.ContainerDatabaseDriver"))
      .set(
        springDatasourceUrl(),
        propertyValue(
          "jdbc:tc:postgresql:" + dockerImage.version() + ":///" + properties.projectBaseName().name() + "?TC_TMPFS=/testtmpfs:rw"
        )
      )
      .set(springDatasourceUsername(), propertyValue(properties.projectBaseName().name()))
      .set(springDatasourcePassword(), propertyValue(""))
      .set(propertyKey("spring.datasource.hikari.maximum-pool-size"), propertyValue("2"));
  }

  private void appendReplacements(JHipsterModuleBuilder builder) {
    builder
      .optionalReplacements()
      .in("src/main/resources/logback-spring.xml")
      .add(text(NEEDLE_LOGBACK_LOGGER), logger(ORG_POSTGRESQL, Level.WARN))
      .add(text(NEEDLE_LOGBACK_LOGGER), logger("org.hibernate.validator", Level.WARN))
      .add(text(NEEDLE_LOGBACK_LOGGER), logger(ORG_HIBERNATE, Level.WARN))
      .add(text(NEEDLE_LOGBACK_LOGGER), logger("org.hibernate.ejb.HibernatePersistence", Level.OFF))
      .and()
      .in("src/test/resources/logback.xml")
      .add(text(NEEDLE_LOGBACK_LOGGER), logger(ORG_POSTGRESQL, Level.WARN))
      .add(text(NEEDLE_LOGBACK_LOGGER), logger("org.hibernate.validator", Level.WARN))
      .add(text(NEEDLE_LOGBACK_LOGGER), logger(ORG_HIBERNATE, Level.WARN))
      .add(text(NEEDLE_LOGBACK_LOGGER), logger("org.hibernate.ejb.HibernatePersistence", Level.OFF))
      .add(text(NEEDLE_LOGBACK_LOGGER), logger("com.github.dockerjava", Level.WARN))
      .add(text(NEEDLE_LOGBACK_LOGGER), logger("org.testcontainers", Level.WARN))
      .add(text(NEEDLE_LOGBACK_LOGGER), logger("org.jboss.logging", Level.WARN))
      .and();
  }

  private PropertyKey springDatasourceUrl() {
    return propertyKey("spring.datasource.url");
  }

  private PropertyKey springDatasourceUsername() {
    return propertyKey("spring.datasource.username");
  }

  private PropertyKey springDatasourcePassword() {
    return propertyKey("spring.datasource.password");
  }

  private PropertyKey springDatasourceDriverClassName() {
    return propertyKey("spring.datasource.driver-class-name");
  }

  private String logger(String loggerName, Level level) {
    return String.format("<logger name=\"%s\" level=\"%s\" />", loggerName, level.toString()) + LF + "  " + NEEDLE_LOGBACK_LOGGER;
  }

  private JavaDependency springDataJpa() {
    return javaDependency().groupId("org.springframework.boot").artifactId("spring-boot-starter-data-jpa").build();
  }

  private JavaDependency psqlDriver() {
    return javaDependency().groupId(ORG_POSTGRESQL).artifactId("postgresql").build();
  }

  private JavaDependency psqlHikari() {
    return javaDependency().groupId("com.zaxxer").artifactId("HikariCP").build();
  }

  private JavaDependency psqlHibernateCore() {
    return javaDependency().groupId(ORG_HIBERNATE).artifactId("hibernate-core").build();
  }

  private JavaDependency testContainer() {
    return javaDependency()
      .groupId("org.testcontainers")
      .artifactId(DatabaseType.POSTGRESQL.id())
      .scope(JavaDependencyScope.TEST)
      .versionSlug("testcontainers")
      .build();
  }
}
