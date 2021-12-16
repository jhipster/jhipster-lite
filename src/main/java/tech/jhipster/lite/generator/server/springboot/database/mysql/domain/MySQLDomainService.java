package tech.jhipster.lite.generator.server.springboot.database.mysql.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVA;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;

import ch.qos.logback.classic.Level;
import java.util.Map;
import java.util.TreeMap;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.logging.domain.SpringBootLoggingService;
import tech.jhipster.lite.generator.server.springboot.properties.domain.SpringBootPropertiesService;

public class MySQLDomainService implements MySQLService {

  public static final String SOURCE = "server/springboot/database/mysql";

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;
  private final SpringBootPropertiesService springBootPropertiesService;
  private final SpringBootLoggingService springBootLoggingService;

  public MySQLDomainService(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootPropertiesService springBootPropertiesService,
    SpringBootLoggingService springBootLoggingService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootPropertiesService = springBootPropertiesService;
    this.springBootLoggingService = springBootLoggingService;
  }

  @Override
  public void init(Project project) {
    addSpringDataJpa(project);
    addMySQLDriver(project);
    addHikari(project);
    addHibernateCore(project);
    addDockerCompose(project);
    addJavaFiles(project);
    addProperties(project);
    addLoggerInConfiguration(project);
    addTestcontainers(project);
  }

  @Override
  public void addSpringDataJpa(Project project) {
    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-data-jpa").build();

    buildToolService.addDependency(project, dependency);
  }

  @Override
  public void addMySQLDriver(Project project) {
    Dependency dependency = Dependency.builder().groupId("mysql").artifactId("mysql-connector-java").build();

    buildToolService.addDependency(project, dependency);
  }

  @Override
  public void addHikari(Project project) {
    Dependency dependency = Dependency.builder().groupId("com.zaxxer").artifactId("HikariCP").build();

    buildToolService.addDependency(project, dependency);
  }

  @Override
  public void addHibernateCore(Project project) {
    Dependency dependency = Dependency.builder().groupId("org.hibernate").artifactId("hibernate-core").build();

    buildToolService.addDependency(project, dependency);
  }

  @Override
  public void addDockerCompose(Project project) {
    project.addDefaultConfig(BASE_NAME);
    project.addConfig("dockerImageName", MySQL.getDockerImageName());
    projectRepository.template(project, SOURCE, "mysql.yml", "src/main/docker", "mysql.yml");
  }

  @Override
  public void addJavaFiles(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    project.addDefaultConfig(BASE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));
    String mysqlPath = "technical/infrastructure/secondary/mysql";

    projectRepository.template(project, SOURCE, "DatabaseConfiguration.java", getPath(MAIN_JAVA, packageNamePath, mysqlPath));
  }

  @Override
  public void addProperties(Project project) {
    String baseName = project.getBaseName().orElse("jhipster");
    String packageName = project.getPackageName().orElse("com.mycompany.myapp");

    springProperties(baseName, packageName).forEach((k, v) -> springBootPropertiesService.addProperties(project, k, v));
  }

  @Override
  public void addLoggerInConfiguration(Project project) {
    addLogger(project, "org.hibernate.validator", Level.WARN);
    addLogger(project, "org.hibernate", Level.WARN);
    addLogger(project, "org.hibernate.ejb.HibernatePersistence", Level.OFF);
  }

  public void addLogger(Project project, String packageName, Level level) {
    springBootLoggingService.addLogger(project, packageName, level);
    springBootLoggingService.addLoggerTest(project, packageName, level);
  }

  @Override
  public void addTestcontainers(Project project) {
    String baseName = project.getBaseName().orElse("jhipster");
    Dependency dependency = Dependency
      .builder()
      .groupId("org.testcontainers")
      .artifactId("mysql")
      .version("\\${testcontainers.version}")
      .scope("test")
      .build();
    buildToolService.addProperty(project, "testcontainers", MySQL.getTestcontainersVersion());
    buildToolService.addDependency(project, dependency);

    springPropertiesForTest(baseName).forEach((k, v) -> springBootPropertiesService.addPropertiesTest(project, k, v));
  }

  private Map<String, Object> springProperties(String baseName, String packageName) {
    TreeMap<String, Object> result = new TreeMap<>();
    result.put("spring.datasource.type", "com.zaxxer.hikari.HikariDataSource");
    result.put("spring.datasource.url", "jdbc:mysql://localhost:3306/" + baseName);
    result.put("spring.datasource.username", "root");
    result.put("spring.datasource.password", "");
    result.put("spring.datasource.hikari.poolName", "Hikari");
    result.put("spring.datasource.hikari.auto-commit", false);

    result.put("spring.data.jpa.repositories.bootstrap-mode", "deferred");
    result.put("spring.jpa.properties.hibernate.jdbc.time_zone", "UTC");
    result.put("spring.jpa.open-in-view", false);
    result.put("spring.jpa.properties.hibernate.id.new_generator_mappings", "true");
    result.put("spring.jpa.properties.hibernate.connection.provider_disables_autocommit", "true");
    result.put("spring.jpa.properties.hibernate.generate_statistics", false);

    result.put("spring.jpa.properties.hibernate.jdbc.batch_size", "25");
    result.put("spring.jpa.properties.hibernate.order_inserts", "true");
    result.put("spring.jpa.properties.hibernate.order_updates", "true");
    result.put("spring.jpa.properties.hibernate.query.fail_on_pagination_over_collection_fetch", "true");
    result.put("spring.jpa.properties.hibernate.query.in_clause_parameter_padding", "true");
    result.put("spring.jpa.hibernate.ddl-auto", "none");
    result.put("spring.jpa.hibernate.naming.physical-strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
    result.put("spring.jpa.hibernate.naming.implicit-strategy", "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
    return result;
  }

  private Map<String, Object> springPropertiesForTest(String baseName) {
    TreeMap<String, Object> result = new TreeMap<>();
    result.put("spring.datasource.driver-class-name", "org.testcontainers.jdbc.ContainerDatabaseDriver");
    result.put("spring.datasource.url", "jdbc:tc:" + MySQL.getDockerImageName() + ":///" + baseName);
    result.put("spring.datasource.username", baseName);
    result.put("spring.datasource.password", "");
    return result;
  }
}
