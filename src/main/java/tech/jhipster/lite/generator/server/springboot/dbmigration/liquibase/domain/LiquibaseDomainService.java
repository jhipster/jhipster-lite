package tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.common.domain.WordUtils.LF;
import static tech.jhipster.lite.common.domain.WordUtils.indent;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PRETTIER_DEFAULT_INDENT;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.domain.Liquibase.NEEDLE_LIQUIBASE;

import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.project.domain.DatabaseType;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.Level;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

public class LiquibaseDomainService implements LiquibaseService {

  public static final String SOURCE = "server/springboot/dbmigration/liquibase";
  public static final String LIQUIBASE_PATH = "technical/infrastructure/secondary/liquibase";
  public static final String CONFIG_LIQUIBASE = "config/liquibase";
  public static final String CHANGELOG = CONFIG_LIQUIBASE + "/changelog";
  public static final String USER_DATABASE_KEY = "sqlDatabaseName";

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;
  private final SpringBootCommonService springBootCommonService;

  public LiquibaseDomainService(
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
    addLiquibase(project);
    addChangelogMasterXml(project);
    addConfigurationJava(project);
    addLoggerInConfiguration(project);
  }

  @Override
  public void addLiquibase(Project project) {
    Dependency liquibaseDependency = Dependency.builder().groupId("org.liquibase").artifactId("liquibase-core").build();
    buildToolService.addDependency(project, liquibaseDependency);

    Dependency h2dependency = Dependency.builder().groupId("com.h2database").artifactId("h2").scope("test").build();
    buildToolService.addDependency(project, h2dependency);
  }

  @Override
  public void addChangelogMasterXml(Project project) {
    projectRepository.add(project, getPath(SOURCE, "resources"), LIQUIBASE_MASTER_XML, getPath(MAIN_RESOURCES, CONFIG_LIQUIBASE));
  }

  @Override
  public void addChangelogXml(Project project, String path, String fileName) {
    int indent = (Integer) project.getConfig(PRETTIER_DEFAULT_INDENT).orElse(2);

    String includeLine = new StringBuilder()
      .append(Liquibase.getIncludeLine(path, fileName))
      .append(LF)
      .append(indent(1, indent))
      .append(NEEDLE_LIQUIBASE)
      .toString();

    if (!projectRepository.containsRegexp(project, getPath(MAIN_RESOURCES, CONFIG_LIQUIBASE), LIQUIBASE_MASTER_XML, includeLine)) {
      projectRepository.replaceText(
        project,
        getPath(MAIN_RESOURCES, CONFIG_LIQUIBASE),
        LIQUIBASE_MASTER_XML,
        NEEDLE_LIQUIBASE,
        includeLine
      );
    }
  }

  @Override
  public void addConfigurationJava(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));

    templateToLiquibase(project, packageNamePath, "src", "AsyncSpringLiquibase.java", MAIN_JAVA);
    templateToLiquibase(project, packageNamePath, "src", "LiquibaseConfiguration.java", MAIN_JAVA);
    templateToLiquibase(project, packageNamePath, "src", "SpringLiquibaseUtil.java", MAIN_JAVA);

    springBootCommonService.addTestLogbackRecorder(project);
    templateToLiquibase(project, packageNamePath, "test", "AsyncSpringLiquibaseTest.java", TEST_JAVA);
    templateToLiquibase(project, packageNamePath, "test", "LiquibaseConfigurationIT.java", TEST_JAVA);
    templateToLiquibase(project, packageNamePath, "test", "SpringLiquibaseUtilTest.java", TEST_JAVA);
  }

  @Override
  public void addLoggerInConfiguration(Project project) {
    addLogger(project, "liquibase", Level.WARN);
    addLogger(project, "LiquibaseSchemaResolver", Level.INFO);
  }

  public void addLogger(Project project, String packageName, Level level) {
    springBootCommonService.addLogger(project, packageName, level);
    springBootCommonService.addLoggerTest(project, packageName, level);
  }

  private void templateToLiquibase(Project project, String source, String type, String sourceFilename, String destination) {
    projectRepository.template(project, getPath(SOURCE, type), sourceFilename, getPath(destination, source, LIQUIBASE_PATH));
  }

  @Override
  public void addSqlUserChangelog(Project project, DatabaseType sqlDatabase) {
    // Update liquibase master file
    addChangelogXml(project, "user/" + sqlDatabase.id(), "user.xml");

    project.addConfig(USER_DATABASE_KEY, sqlDatabase.id());

    // Copy liquibase files
    projectRepository.template(project, getUserResourcePath(), "user.xml", getSqlLiquibasePath(sqlDatabase));
    projectRepository.add(project, getUserResourcePath(), "user.csv", getSqlLiquibasePath(sqlDatabase));
  }

  private String getUserResourcePath() {
    return getPath(SOURCE, "resources/user");
  }

  @Override
  public void addSqlUserAuthorityChangelog(Project project, DatabaseType sqlDatabase) {
    // Update liquibase master file
    addChangelogXml(project, "user/" + sqlDatabase.id(), "authority.xml");

    project.addConfig(USER_DATABASE_KEY, sqlDatabase.id());

    // Copy liquibase files
    projectRepository.template(project, getUserResourcePath(), "authority.xml", getSqlLiquibasePath(sqlDatabase));
    projectRepository.add(project, getUserResourcePath(), "authority.csv", getSqlLiquibasePath(sqlDatabase));
    projectRepository.add(project, getUserResourcePath(), "user_authority.csv", getSqlLiquibasePath(sqlDatabase));
  }

  private String getSqlLiquibasePath(DatabaseType sqlDatabase) {
    return getPath(MAIN_RESOURCES, CHANGELOG + "/user/" + sqlDatabase.id());
  }
}
