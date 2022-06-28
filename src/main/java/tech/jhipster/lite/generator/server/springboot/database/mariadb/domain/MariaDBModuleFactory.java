package tech.jhipster.lite.generator.server.springboot.database.mariadb.domain;

import static tech.jhipster.lite.common.domain.WordUtils.LF;
import static tech.jhipster.lite.generator.module.domain.JHipsterModule.*;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.NEEDLE_LOGBACK_LOGGER;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.docker.domain.DockerImage;
import tech.jhipster.lite.generator.docker.domain.DockerImages;
import tech.jhipster.lite.generator.module.domain.JHipsterDestination;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependencyScope;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.project.domain.DatabaseType;
import tech.jhipster.lite.generator.server.springboot.common.domain.Level;

public class MariaDBModuleFactory {

  private static final String SOURCE_FOLDER = "server/springboot/database/mariadb";
  private static final String ORG_HIBERNATE = "org.hibernate";
  private static final String FALSE = "false";
  private static final String TRUE = "true";

  private final DockerImages dockerImages;

  public MariaDBModuleFactory(DockerImages dockerImages) {
    Assert.notNull("dockerImages", dockerImages);

    this.dockerImages = dockerImages;
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    DockerImage dockerImage = dockerImages.get("mariadb");

    JHipsterModuleBuilder builder = moduleBuilder(properties);
    appendContext(builder, properties, dockerImage);
    appendDocumentation(builder);
    appendFiles(builder, properties);
    appendJavaDependencies(builder);
    appendSpringProperties(builder, properties, dockerImage);
    appendReplacements(builder);

    return builder.build();
  }

  private void appendContext(JHipsterModuleBuilder builder, JHipsterModuleProperties properties, DockerImage dockerImage) {
    builder
      .context()
      .put("applicationName", properties.projectBaseName().capitalized())
      .put("srcMainDocker", "src/main/docker") // Used in mariadb.md
      .put("mariaDBDockerImageWithVersion", dockerImage.fullName()); // Used in mariadb.yml
  }

  private void appendDocumentation(JHipsterModuleBuilder builder) {
    builder.documentation(documentationTitle("MariaDB"), from(SOURCE_FOLDER).template("mariadb.md"));
  }

  private void appendFiles(JHipsterModuleBuilder builder, JHipsterModuleProperties properties) {
    JHipsterDestination mainDestination = toSrcMainJava()
      .append(properties.basePackage().path())
      .append("technical/infrastructure/secondary/mariadb");

    builder
      .files()
      .add(from(SOURCE_FOLDER).template("DatabaseConfiguration.java"), mainDestination.append("DatabaseConfiguration.java"))
      .add(from(SOURCE_FOLDER).template("mariadb.yml"), toSrcMainDocker().append("mariadb.yml"));
  }

  private void appendJavaDependencies(JHipsterModuleBuilder builder) {
    builder
      .javaDependencies()
      .add(groupId("org.springframework.boot"), artifactId("spring-boot-starter-data-jpa"))
      .add(groupId("org.mariadb.jdbc"), artifactId("mariadb-java-client"))
      .add(groupId("com.zaxxer"), artifactId("HikariCP"))
      .add(groupId(ORG_HIBERNATE), artifactId("hibernate-core"))
      .add(testContainer());
  }

  private JavaDependency testContainer() {
    return javaDependency()
      .groupId("org.testcontainers")
      .artifactId(DatabaseType.MARIADB.id())
      .versionSlug("testcontainers")
      .scope(JavaDependencyScope.TEST)
      .build();
  }

  private void appendSpringProperties(JHipsterModuleBuilder builder, JHipsterModuleProperties properties, DockerImage dockerImage) {
    builder
      .springMainProperties()
      .set(propertyKey("spring.datasource.url"), propertyValue("jdbc:mariadb://localhost:3306/" + properties.projectBaseName().name()))
      .set(propertyKey("spring.datasource.username"), propertyValue("root"))
      .set(propertyKey("spring.datasource.password"), propertyValue(""))
      .set(propertyKey("spring.datasource.driver-class-name"), propertyValue("org.mariadb.jdbc.Driver"))
      .set(propertyKey("spring.datasource.type"), propertyValue("com.zaxxer.hikari.HikariDataSource"))
      .set(propertyKey("spring.datasource.hikari.poolName"), propertyValue("Hikari"))
      .set(propertyKey("spring.datasource.hikari.auto-commit"), propertyValue(FALSE))
      .set(propertyKey("spring.data.jpa.repositories.bootstrap-mode"), propertyValue("deferred"))
      .set(propertyKey("spring.jpa.hibernate.ddl-auto"), propertyValue("none"))
      .set(
        propertyKey("spring.jpa.hibernate.naming.implicit-strategy"),
        propertyValue("org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy")
      )
      .set(
        propertyKey("spring.jpa.hibernate.naming.physical-strategy"),
        propertyValue("org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy")
      )
      .set(propertyKey("spring.jpa.open-in-view"), propertyValue(FALSE))
      .set(propertyKey("spring.jpa.properties.hibernate.connection.provider_disables_autocommit"), propertyValue(TRUE))
      .set(propertyKey("spring.jpa.properties.hibernate.generate_statistics"), propertyValue(FALSE))
      .set(propertyKey("spring.jpa.properties.hibernate.id.new_generator_mappings"), propertyValue(TRUE))
      .set(propertyKey("spring.jpa.properties.hibernate.jdbc.batch_size"), propertyValue("25"))
      .set(propertyKey("spring.jpa.properties.hibernate.jdbc.time_zone"), propertyValue("UTC"))
      .set(propertyKey("spring.jpa.properties.hibernate.order_inserts"), propertyValue(TRUE))
      .set(propertyKey("spring.jpa.properties.hibernate.order_updates"), propertyValue(TRUE))
      .set(propertyKey("spring.jpa.properties.hibernate.query.fail_on_pagination_over_collection_fetch"), propertyValue(TRUE))
      .set(propertyKey("spring.jpa.properties.hibernate.query.in_clause_parameter_padding"), propertyValue(TRUE));

    builder
      .springTestProperties()
      .set(
        propertyKey("spring.datasource.url"),
        propertyValue("jdbc:tc:" + dockerImage.fullName() + ":///" + properties.projectBaseName().name())
      )
      .set(propertyKey("spring.datasource.username"), propertyValue(properties.projectBaseName().name()))
      .set(propertyKey("spring.datasource.password"), propertyValue(""))
      .set(propertyKey("spring.datasource.driver-class-name"), propertyValue("org.testcontainers.jdbc.ContainerDatabaseDriver"))
      .set(propertyKey("spring.datasource.hikari.maximum-pool-size"), propertyValue("2"));
  }

  private void appendReplacements(JHipsterModuleBuilder builder) {
    builder
      .optionalReplacements()
      .in("src/main/resources/logback-spring.xml")
      .add(text(NEEDLE_LOGBACK_LOGGER), logger("org.hibernate.validator", Level.WARN))
      .add(text(NEEDLE_LOGBACK_LOGGER), logger(ORG_HIBERNATE, Level.WARN))
      .add(text(NEEDLE_LOGBACK_LOGGER), logger("org.hibernate.ejb.HibernatePersistence", Level.OFF));

    builder
      .optionalReplacements()
      .in("src/test/resources/logback.xml")
      .add(text(NEEDLE_LOGBACK_LOGGER), logger("org.hibernate.validator", Level.WARN))
      .add(text(NEEDLE_LOGBACK_LOGGER), logger(ORG_HIBERNATE, Level.WARN))
      .add(text(NEEDLE_LOGBACK_LOGGER), logger("org.hibernate.ejb.HibernatePersistence", Level.OFF))
      .add(text(NEEDLE_LOGBACK_LOGGER), logger("com.github.dockerjava", Level.WARN))
      .add(text(NEEDLE_LOGBACK_LOGGER), logger("org.testcontainers", Level.WARN));
  }

  private String logger(String loggerName, Level level) {
    return String.format("<logger name=\"%s\" level=\"%s\" />", loggerName, level.toString()) + LF + "  " + NEEDLE_LOGBACK_LOGGER;
  }
}
