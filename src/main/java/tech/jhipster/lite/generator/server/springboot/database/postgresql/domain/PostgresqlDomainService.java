package tech.jhipster.lite.generator.server.springboot.database.postgresql.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVA;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.lite.generator.server.springboot.database.postgresql.domain.Postgresql.*;

import java.util.Map;
import java.util.TreeMap;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.Level;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

public class PostgresqlDomainService implements PostgresqlService {

  public static final String SOURCE = "server/springboot/database/postgresql";

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;
  private final SpringBootCommonService springBootCommonService;

  public PostgresqlDomainService(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootCommonService springBootCommonService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
  }

  @Override
  public void init(Project project) {
    addSpringDataJpa(project);
    addPostgreSQLDriver(project);
    addHikari(project);
    addHibernateCore(project);
    addDockerCompose(project);
    addJavaFiles(project);
    addProperties(project);
    addTestcontainers(project);
    addLoggerInConfiguration(project);
  }

  @Override
  public void addSpringDataJpa(Project project) {
    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-data-jpa").build();

    buildToolService.addDependency(project, dependency);
  }

  @Override
  public void addPostgreSQLDriver(Project project) {
    buildToolService.addDependency(project, psqlDriver());
  }

  @Override
  public void addHikari(Project project) {
    buildToolService.addDependency(project, psqlHikari());
  }

  @Override
  public void addHibernateCore(Project project) {
    buildToolService.addDependency(project, psqlHibernateCore());
  }

  @Override
  public void addDockerCompose(Project project) {
    project.addDefaultConfig(BASE_NAME);
    project.addConfig("postgresqlDockerImage", Postgresql.getPostgresqlDockerImage());
    projectRepository.template(project, SOURCE, "postgresql.yml", "src/main/docker", "postgresql.yml");
  }

  @Override
  public void addJavaFiles(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    project.addDefaultConfig(BASE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));
    String postgresqlPath = "technical/infrastructure/secondary/postgresql";

    projectRepository.template(project, SOURCE, "DatabaseConfiguration.java", getPath(MAIN_JAVA, packageNamePath, postgresqlPath));
    projectRepository.template(project, SOURCE, "FixedPostgreSQL10Dialect.java", getPath(MAIN_JAVA, packageNamePath, postgresqlPath));

    projectRepository.template(project, SOURCE, "FixedPostgreSQL10DialectTest.java", getPath(TEST_JAVA, packageNamePath, postgresqlPath));
  }

  @Override
  public void addProperties(Project project) {
    String baseName = project.getBaseName().orElse("jhipster");
    String packageName = project.getPackageName().orElse("com.mycompany.myapp");

    springPropertiesDatasource(baseName).forEach((k, v) -> springBootCommonService.addProperties(project, k, v));
    springPropertiesJpaPart1(packageName).forEach((k, v) -> springBootCommonService.addProperties(project, k, v));
    springPropertiesJpaPart2().forEach((k, v) -> springBootCommonService.addProperties(project, k, v));
    springPropertiesHibernate().forEach((k, v) -> springBootCommonService.addProperties(project, k, v));
  }

  @Override
  public void addTestcontainers(Project project) {
    String baseName = project.getBaseName().orElse("jhipster");
    Dependency dependency = Dependency
      .builder()
      .groupId("org.testcontainers")
      .artifactId("postgresql")
      .version("\\${testcontainers.version}")
      .scope("test")
      .build();
    buildToolService.addProperty(project, "testcontainers.version", Postgresql.getTestcontainersVersion());
    buildToolService.addDependency(project, dependency);

    springPropertiesForTest(baseName).forEach((k, v) -> springBootCommonService.addPropertiesTest(project, k, v));
  }

  @Override
  public void addLoggerInConfiguration(Project project) {
    addLogger(project, "org.hibernate.validator", Level.WARN);
    addLogger(project, "org.hibernate", Level.WARN);
    addLogger(project, "org.hibernate.ejb.HibernatePersistence", Level.OFF);
    addLogger(project, "org.postgresql", Level.WARN);

    springBootCommonService.addLoggerTest(project, "com.github.dockerjava", Level.WARN);
    springBootCommonService.addLoggerTest(project, "org.testcontainers", Level.WARN);
  }

  private Map<String, Object> springPropertiesDatasource(String baseName) {
    TreeMap<String, Object> result = new TreeMap<>();

    result.put("spring.datasource.type", "com.zaxxer.hikari.HikariDataSource");
    result.put("spring.datasource.url", "jdbc:postgresql://localhost:5432/" + baseName);
    result.put("spring.datasource.username", baseName);
    result.put("spring.datasource.password", "");
    result.put("spring.datasource.driver-class-name", "org.postgresql.Driver");
    result.put("spring.datasource.hikari.poolName", "Hikari");
    result.put("spring.datasource.hikari.auto-commit", false);

    return result;
  }

  private Map<String, Object> springPropertiesJpaPart1(String packageName) {
    TreeMap<String, Object> result = new TreeMap<>();

    result.put("spring.data.jpa.repositories.bootstrap-mode", "deferred");
    result.put("spring.jpa.database-platform", packageName + ".technical.infrastructure.secondary.postgresql.FixedPostgreSQL10Dialect");
    result.put("spring.jpa.properties.hibernate.jdbc.time_zone", "UTC");
    result.put("spring.jpa.open-in-view", false);
    result.put("spring.jpa.properties.hibernate.id.new_generator_mappings", "true");
    result.put("spring.jpa.properties.hibernate.connection.provider_disables_autocommit", "true");
    result.put("spring.jpa.properties.hibernate.generate_statistics", false);

    return result;
  }

  private Map<String, Object> springPropertiesJpaPart2() {
    TreeMap<String, Object> result = new TreeMap<>();

    result.put("spring.jpa.properties.hibernate.jdbc.batch_size", "25");
    result.put("spring.jpa.properties.hibernate.order_inserts", "true");
    result.put("spring.jpa.properties.hibernate.order_updates", "true");
    result.put("spring.jpa.properties.hibernate.query.fail_on_pagination_over_collection_fetch", "true");
    result.put("spring.jpa.properties.hibernate.query.in_clause_parameter_padding", "true");

    return result;
  }

  private Map<String, Object> springPropertiesHibernate() {
    TreeMap<String, Object> result = new TreeMap<>();

    result.put("spring.jpa.hibernate.ddl-auto", "none");
    result.put("spring.jpa.hibernate.naming.physical-strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
    result.put("spring.jpa.hibernate.naming.implicit-strategy", "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");

    return result;
  }

  private Map<String, Object> springPropertiesForTest(String baseName) {
    TreeMap<String, Object> result = new TreeMap<>();
    result.put("spring.datasource.driver-class-name", "org.testcontainers.jdbc.ContainerDatabaseDriver");
    result.put(
      "spring.datasource.url",
      "jdbc:tc:postgresql:" + Postgresql.getPostgresqlDockerVersion() + ":///" + baseName + "?TC_TMPFS=/testtmpfs:rw"
    );
    result.put("spring.datasource.username", baseName);
    result.put("spring.datasource.password", "");
    result.put("spring.datasource.hikari.maximum-pool-size", 2);
    return result;
  }

  public void addLogger(Project project, String packageName, Level level) {
    springBootCommonService.addLogger(project, packageName, level);
    springBootCommonService.addLoggerTest(project, packageName, level);
  }
}
