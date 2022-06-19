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
  private static final String SRC_MAIN_DOCKER = "src/main/docker";
  public static final String LOGGING_TEST_CONFIGURATION = "src/test/resources/logback.xml";

  private final DockerImages dockerImages;

  public MariaDBModuleFactory(DockerImages dockerImages) {
    Assert.notNull("dockerImages", dockerImages);

    this.dockerImages = dockerImages;
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String packagePath = properties.basePackage().path();
    String baseClassName = properties.projectBaseName().capitalized();
    JHipsterDestination mainDestination = toSrcMainJava().append(packagePath).append("technical/infrastructure/secondary/mariadb");

    DockerImage mariaDBDockerImage = dockerImages.get("mariadb");

    //@formatter:off
    JHipsterModuleBuilder builder = moduleBuilder(properties)
      .context()
        .packageName(properties.basePackage())
        .put("applicationName", baseClassName)
        .put("srcMainDocker", SRC_MAIN_DOCKER) // Used in mariadb.md
        .put("mariaDBDockerImageWithVersion", mariaDBDockerImage.fullName()) // Used in mariadb.yml
        .and()
      .documentation(documentationTitle("MariaDB"), from(SOURCE_FOLDER).template("mariadb.md"))
      .files()
        .add(from(SOURCE_FOLDER).template("DatabaseConfiguration.java"), mainDestination.append("DatabaseConfiguration.java"))
        .add(from(SOURCE_FOLDER).template("mariadb.yml"), toSrcMainDocker().append("mariadb.yml"))
        .and()
      .javaDependencies()
        .add(groupId("org.springframework.boot"), artifactId("spring-boot-starter-data-jpa"))
        .add(groupId("org.mariadb.jdbc"), artifactId("mariadb-java-client"))
        .add(groupId("com.zaxxer"), artifactId("HikariCP"))
        .add(groupId("org.hibernate"), artifactId("hibernate-core"))
        .add(testContainer())
        .and()
      .springMainProperties()
        .set(propertyKey("spring.datasource.url"), propertyValue("jdbc:mariadb://localhost:3306/" + properties.projectBaseName().name()))
        .set(propertyKey("spring.datasource.username"), propertyValue("root"))
        .set(propertyKey("spring.datasource.password"), propertyValue(""))
        .set(propertyKey("spring.datasource.driver-class-name"), propertyValue("org.mariadb.jdbc.Driver"))
        .and()
      .springTestProperties()
        .set(propertyKey("spring.datasource.url"), propertyValue("jdbc:tc:" + mariaDBDockerImage.fullName() + ":///" + properties.projectBaseName().name()))
        .set(propertyKey("spring.datasource.username"), propertyValue(properties.projectBaseName().name()))
        .set(propertyKey("spring.datasource.password"), propertyValue(""))
        .set(propertyKey("spring.datasource.driver-class-name"), propertyValue("org.testcontainers.jdbc.ContainerDatabaseDriver"))
        .set(propertyKey("spring.datasource.hikari.maximum-pool-size"), propertyValue("2"))
        .and()
      .optionalReplacements()
        .in(LOGGING_TEST_CONFIGURATION)
          .add(text(NEEDLE_LOGBACK_LOGGER), logger("com.github.dockerjava", Level.WARN))
          .add(text(NEEDLE_LOGBACK_LOGGER), logger("org.testcontainers", Level.WARN))
          .and()
        .and();
    //@formatter:on

    return builder.build();
  }

  private JavaDependency testContainer() {
    return javaDependency()
      .groupId("org.testcontainers")
      .artifactId(DatabaseType.MARIADB.id())
      .versionSlug("testcontainers")
      .scope(JavaDependencyScope.TEST)
      .build();
  }

  private String logger(String loggerName, Level level) {
    return String.format("<logger name=\"%s\" level=\"%s\" />", loggerName, level.toString()) + LF + "  " + NEEDLE_LOGBACK_LOGGER;
  }
}
