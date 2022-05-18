package tech.jhipster.lite.generator.server.springboot.database.mssql.domain;

import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.docker.domain.DockerService;
import tech.jhipster.lite.generator.project.domain.DatabaseType;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.Level;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.database.sqlcommon.domain.SQLCommonService;

public class MssqlDomainService implements MssqlService {

  public static final String SOURCE = "server/sql";

  private final BuildToolService buildToolService;
  private final SpringBootCommonService springBootCommonService;
  private final SQLCommonService sqlCommonService;
  private final DockerService dockerService;
  private final ProjectRepository projectRepository;

  public MssqlDomainService(
    BuildToolService buildToolService,
    SpringBootCommonService springBootCommonService,
    SQLCommonService sqlCommonService,
    DockerService dockerService,
    ProjectRepository projectRepository
  ) {
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
    this.sqlCommonService = sqlCommonService;
    this.dockerService = dockerService;
    this.projectRepository = projectRepository;
  }

  @Override
  public void init(Project project) {
    Assert.notNull("project", project);

    addSpringData(project);
    addDriver(project);
    addDockerCompose(project);
    addJavaFiles(project);
    addHikari(project);
    addProperties(project);
    addHibernateCore(project);
    addTestcontainers(project);
    addLoggerInConfiguration(project);
  }

  private void addSpringData(Project project) {
    sqlCommonService.addSpringDataJpa(project);
  }

  private void addDriver(Project project) {
    buildToolService.addDependency(project, Mssql.driver());
  }

  private void addDockerCompose(Project project) {
    project.addDefaultConfig(BASE_NAME);

    dockerService
      .getImageNameWithVersion(Mssql.getDockerImageName())
      .ifPresentOrElse(
        imageName -> project.addConfig("dockerImageName", imageName),
        () -> {
          throw new GeneratorException("Version not found for docker image: " + Mssql.getDockerImageName());
        }
      );
    sqlCommonService.addDockerComposeTemplate(project, DatabaseType.MSSQL.id());
  }

  private void addHikari(Project project) {
    sqlCommonService.addHikari(project);
  }

  private void addJavaFiles(Project project) {
    sqlCommonService.addJavaFiles(project, DatabaseType.MSSQL.id());
  }

  private void addProperties(Project project) {
    springBootCommonService.addPropertiesComment(project, "Database Configuration");

    Mssql
      .springProperties(project.getBaseName().orElse("jhipster"))
      .forEach((k, v) -> springBootCommonService.addProperties(project, k, v));
    springBootCommonService.addPropertiesNewLine(project);
  }

  private void addTestcontainers(Project project) {
    this.sqlCommonService.addTestcontainers(
        project,
        DatabaseType.MSSQL.id(),
        Mssql.springPropertiesForTest(project.getBaseName().orElse("jhipster"))
      );
    this.projectRepository.add(
        ProjectFile.forProject(project).withSource(SOURCE, Mssql.LICENSE_TEST_CONTAINER_FILE).withDestinationFolder(TEST_RESOURCES)
      );
  }

  private void addHibernateCore(Project project) {
    sqlCommonService.addHibernateCore(project);
  }

  private void addLoggerInConfiguration(Project project) {
    sqlCommonService.addLoggers(project);
    addLogger(project, "org.reflections", Level.WARN);
    addLogger(project, Mssql.driver().getGroupId(), Level.WARN);

    springBootCommonService.addLoggerTest(project, "com.github.dockerjava", Level.WARN);
    springBootCommonService.addLoggerTest(project, "org.testcontainers", Level.WARN);
  }

  private void addLogger(Project project, String packageName, Level level) {
    springBootCommonService.addLogger(project, packageName, level);
    springBootCommonService.addLoggerTest(project, packageName, level);
  }
}
