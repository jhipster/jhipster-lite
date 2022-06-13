package tech.jhipster.lite.generator.server.springboot.database.postgresql.domain;

import static tech.jhipster.lite.common.domain.FileUtils.*;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;
import static tech.jhipster.lite.generator.server.springboot.database.postgresql.domain.Postgresql.*;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.docker.domain.DockerImages;
import tech.jhipster.lite.generator.project.domain.DatabaseType;
import tech.jhipster.lite.generator.project.domain.DefaultConfig;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.Level;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.database.sqlcommon.domain.SQLCommonService;

public class PostgresqlDomainService implements PostgresqlService {

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;
  private final SpringBootCommonService springBootCommonService;
  private final SQLCommonService sqlCommonService;
  private final DockerImages dockerImages;

  public PostgresqlDomainService(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootCommonService springBootCommonService,
    SQLCommonService sqlCommonService,
    DockerImages dockerImages
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
    this.sqlCommonService = sqlCommonService;
    this.dockerImages = dockerImages;
  }

  @Override
  public void init(Project project) {
    Assert.notNull("project", project);

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
    sqlCommonService.addSpringDataJpa(project);
  }

  @Override
  public void addPostgreSQLDriver(Project project) {
    buildToolService.addDependency(project, psqlDriver());
  }

  @Override
  public void addHikari(Project project) {
    sqlCommonService.addHikari(project);
  }

  @Override
  public void addHibernateCore(Project project) {
    sqlCommonService.addHibernateCore(project);
  }

  @Override
  public void addDockerCompose(Project project) {
    project.addDefaultConfig(BASE_NAME);

    String dockerImage = dockerImages.get(Postgresql.getPostgresqlDockerImageName()).fullName();
    project.addConfig("postgresqlDockerImage", dockerImage);

    sqlCommonService.addDockerComposeTemplate(project, DatabaseType.POSTGRESQL.id());
  }

  @Override
  public void addJavaFiles(Project project) {
    sqlCommonService.addJavaFiles(project, DatabaseType.POSTGRESQL.id());
    String packageNamePath = project.getPackageNamePath().orElse(getPath(DefaultConfig.PACKAGE_PATH));
    String databasePath = "technical/infrastructure/secondary/postgresql";

    projectRepository.template(
      ProjectFile
        .forProject(project)
        .withSource(SOURCE, "FixedPostgreSQL10Dialect.java")
        .withDestinationFolder(getPath(MAIN_JAVA, packageNamePath, databasePath))
    );
    projectRepository.template(
      ProjectFile
        .forProject(project)
        .withSource(SOURCE, "FixedPostgreSQL10DialectTest.java")
        .withDestinationFolder(getPath(TEST_JAVA, packageNamePath, databasePath))
    );
  }

  @Override
  public void addProperties(Project project) {
    String baseName = project.getBaseName().orElse("jhipster");
    String packageName = project.getPackageName().orElse(DefaultConfig.DEFAULT_PACKAGE_NAME);

    sqlCommonService.addProperties(project, springProperties(baseName, packageName));
  }

  private void addTestcontainers(Project project) {
    String baseName = project.getBaseName().orElse("jhipster");
    String imageVersion = dockerImages.get(Postgresql.getPostgresqlDockerImageName()).version();
    this.sqlCommonService.addTestcontainers(project, DatabaseType.POSTGRESQL.id(), springPropertiesForTest(baseName, imageVersion));
  }

  @Override
  public void addLoggerInConfiguration(Project project) {
    sqlCommonService.addLoggers(project);
    addLogger(project, "org.postgresql", Level.WARN);

    springBootCommonService.addLoggerTest(project, "com.github.dockerjava", Level.WARN);
    springBootCommonService.addLoggerTest(project, "org.testcontainers", Level.WARN);
    springBootCommonService.addLoggerTest(project, "org.jboss.logging", Level.WARN);
  }

  public void addLogger(Project project, String packageName, Level level) {
    springBootCommonService.addLogger(project, packageName, level);
    springBootCommonService.addLoggerTest(project, packageName, level);
  }
}
