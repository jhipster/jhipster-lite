package tech.jhipster.lite.generator.server.springboot.database.postgresql.domain;

import static tech.jhipster.lite.generator.module.domain.JHipsterModule.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.docker.domain.DockerService;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.JHipsterSource;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.generator.project.domain.DatabaseType;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.common.domain.Level;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.database.sqlcommon.domain.SQLCommonService;

public class PostgresqlModuleFactory {

  private static final String SOURCE = "server/springboot/database/postgresql";

  public static final String POSTGRESQL_DOCKER_IMAGE_NAME = "postgres";

  public static final String SRC_MAIN_DOCKER = "src/main/docker";

  private final DockerService dockerService;

  private final SpringBootCommonService springBootCommonService;
  private final SQLCommonService sqlCommonService;

  public PostgresqlModuleFactory(
    DockerService dockerService,
    SpringBootCommonService springBootCommonService,
    SQLCommonService sqlCommonService
  ) {
    this.dockerService = dockerService;
    this.springBootCommonService = springBootCommonService;
    this.sqlCommonService = sqlCommonService;
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String packagePath = properties.basePackage().path();
    String applicationName = properties.projectBaseName().capitalized();
    JHipsterSource source = from(SOURCE);
    String databasePath = "technical/infrastructure/secondary/postgresql";
    // fix unmodifiable list bug with prettierDefaultIndent key add
    //  MavenDomainService line 80 : project.addDefaultConfig(PRETTIER_DEFAULT_INDENT);
    var projectPropertie = new HashMap<>(properties.properties());
    Project project = Project.builder().folder(properties.projectFolder().folder()).config(projectPropertie).build();
    String dockerImageVersion = dockerService.getImageVersion(getPostgresqlDockerImageName()).orElseThrow();
    //@formatter:off
    return moduleForProject(properties)
      .context()
      .packageName(properties.basePackage())
      .put("applicationName", applicationName)
      .put("srcDocker", SRC_MAIN_DOCKER)
      .and()
      .documentation(documentationTitle("Postgresql"), from("server/springboot/database/postgresql/postgresql.md.mustache"))
      .files()
      .add(source.template("DatabaseConfiguration.java"), toSrcMainJava().append(packagePath).append(databasePath).append("DatabaseConfiguration.java"))
      .add(source.template("FixedPostgreSQL10Dialect.java"), toSrcMainJava().append(packagePath).append(databasePath).append("FixedPostgreSQL10Dialect.java"))
      .add(source.template("FixedPostgreSQL10DialectTest.java"), toSrcTestJava().append(packagePath).append(databasePath).append("FixedPostgreSQL10DialectTest.java"))
      .and()
      .javaDependencies()
      .add(psqlDriver())
      .add(psqlHikari())
      .add(psqlHibernateCore())
      .and()
      .springMainProperties()
      .set(propertyKey("spring.datasource.url"), propertyValue("jdbc:postgresql://localhost:5432/" + properties.projectBaseName()))
      .set(propertyKey("spring.datasource.username"), propertyValue(properties.projectBaseName().capitalized()))
      .set(propertyKey("spring.datasource.password"), propertyValue(""))
      .set(propertyKey("spring.datasource.driver-class-name"), propertyValue("org.postgresql.Driver"))
      .set(propertyKey("spring.jpa.database-platform"), propertyValue(properties.basePackage() + ".technical.infrastructure.secondary.postgresql.FixedPostgreSQL10Dialect"))
      .and()
      .springTestProperties()
      .set(propertyKey("spring.datasource.driver-class-name"), propertyValue("org.testcontainers.jdbc.ContainerDatabaseDriver"))
      .set(propertyKey("spring.datasource.url"), propertyValue("jdbc:tc:postgresql:" + dockerImageVersion + ":///" + properties.projectBaseName() + "?TC_TMPFS=/testtmpfs:rw"))
      .set(propertyKey("spring.datasource.username"), propertyValue(properties.projectBaseName().capitalized()))
      .set(propertyKey("spring.datasource.password"), propertyValue(""))
      .set(propertyKey("spring.datasource.hikari.maximum-pool-siz"), propertyValue("2"))
      .and()
      .preActions()
      .add(() -> sqlCommonService.addSpringDataJpa(project))
      .and()
      .postActions()
      .add(() -> addDockerCompose(project))
      .add(() -> addTestcontainers(project))
      .add(() -> addDockerCompose(project))
      .add(() -> addLoggerInConfiguration(project))
      .and()
      .build();
    //@formatter:on
  }

  public void addTestcontainers(Project project) {
    String baseName = project.getBaseName().orElse("jhipster");
    String imageVersion = dockerService.getImageVersion(getPostgresqlDockerImageName()).orElseThrow();
    this.sqlCommonService.addTestcontainers(project, DatabaseType.POSTGRESQL.id(), springPropertiesForTest(baseName, imageVersion));
  }

  public void addDockerCompose(Project project) {
    project.addDefaultConfig(BASE_NAME);

    dockerService
      .getImageNameWithVersion(getPostgresqlDockerImageName())
      .ifPresentOrElse(
        imageName -> project.addConfig("postgresqlDockerImage", imageName),
        () -> {
          throw new GeneratorException("Version not found for docker image: " + getPostgresqlDockerImageName());
        }
      );

    sqlCommonService.addDockerComposeTemplate(project, DatabaseType.POSTGRESQL.id());
  }

  public void addLoggerInConfiguration(Project project) {
    sqlCommonService.addLoggers(project);
    addLogger(project, "org.postgresql", Level.WARN);

    springBootCommonService.addLoggerTest(project, "com.github.dockerjava", Level.WARN);
    springBootCommonService.addLoggerTest(project, "org.testcontainers", Level.WARN);
    springBootCommonService.addLoggerTest(project, "org.jboss.logging", Level.WARN);
  }

  private void addLogger(Project project, String packageName, Level level) {
    springBootCommonService.addLogger(project, packageName, level);
    springBootCommonService.addLoggerTest(project, packageName, level);
  }

  public String getPostgresqlDockerImageName() {
    return POSTGRESQL_DOCKER_IMAGE_NAME;
  }

  private JavaDependency psqlDriver() {
    return javaDependency().groupId("org.postgresql").artifactId("postgresql").build();
  }

  private JavaDependency psqlHikari() {
    return javaDependency().groupId("com.zaxxer").artifactId("HikariCP").build();
  }

  private JavaDependency psqlHibernateCore() {
    return javaDependency().groupId("org.hibernate").artifactId("hibernate-core").build();
  }

  public static Map<String, Object> springPropertiesForTest(String baseName, String postgresqlVersion) {
    TreeMap<String, Object> result = new TreeMap<>();
    result.put("spring.datasource.driver-class-name", "org.testcontainers.jdbc.ContainerDatabaseDriver");
    result.put("spring.datasource.url", "jdbc:tc:postgresql:" + postgresqlVersion + ":///" + baseName + "?TC_TMPFS=/testtmpfs:rw");
    result.put("spring.datasource.username", baseName);
    result.put("spring.datasource.password", "");
    result.put("spring.datasource.hikari.maximum-pool-size", 2);
    return result;
  }
}
