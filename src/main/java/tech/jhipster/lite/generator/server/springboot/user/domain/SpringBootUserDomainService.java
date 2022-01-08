package tech.jhipster.lite.generator.server.springboot.user.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;

import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.domain.LiquibaseDomainService;

public class SpringBootUserDomainService implements SpringBootUserService {

  public static final String SOURCE = "server/springboot/user/sqldatabase";
  public static final String TARGET_JAVA = "user/infrastructure/secondary";
  public static final String TARGET_RESOURCE = "config/liquibase/changelog/user";
  public static final String USER_DATABASE_KEY = "sqlDatabaseName";

  private final ProjectRepository projectRepository;
  private final LiquibaseDomainService liquibaseDomainService;

  public SpringBootUserDomainService(ProjectRepository projectRepository, LiquibaseDomainService liquibaseDomainService) {
    this.projectRepository = projectRepository;
    this.liquibaseDomainService = liquibaseDomainService;
  }

  @Override
  public void addSqlJavaUser(Project project, String sqlDatabaseName) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));
    project.addConfig(USER_DATABASE_KEY, sqlDatabaseName);

    projectRepository.template(project, SOURCE, "User.java", getSqlJavaPath(packageNamePath, sqlDatabaseName));
    projectRepository.template(project, SOURCE, "UserConstants.java", getSqlJavaPath(packageNamePath, sqlDatabaseName));
    projectRepository.template(project, SOURCE, "UserConstants.java", getSqlJavaPath(packageNamePath, sqlDatabaseName));
    projectRepository.template(project, SOURCE, "UserJpaRepository.java", getSqlJavaPath(packageNamePath, sqlDatabaseName));
  }

  @Override
  public void addSqlJavaAuthority(Project project, String sqlDatabaseName) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));
    project.addConfig(USER_DATABASE_KEY, sqlDatabaseName);

    projectRepository.template(project, SOURCE, "Authority.java", getSqlJavaPath(packageNamePath, sqlDatabaseName));
    projectRepository.template(project, SOURCE, "AuthorityRepository.java", getSqlJavaPath(packageNamePath, sqlDatabaseName));
  }

  @Override
  public void addSqlJavaAuditEntity(Project project, String sqlDatabaseName) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));
    project.addConfig(USER_DATABASE_KEY, sqlDatabaseName);

    projectRepository.template(project, SOURCE, "AbstractAuditingEntity.java", getSqlJavaPath(packageNamePath, sqlDatabaseName));
  }

  private String getSqlJavaPath(String packageNamePath, String sqlDatabaseName) {
    return getPath(MAIN_JAVA, packageNamePath, TARGET_JAVA + "/" + sqlDatabaseName);
  }

  @Override
  public void addSqlLiquibaseConfiguration(Project project, String sqlDatabaseName) {
    // Update liquibase master file
    liquibaseDomainService.addChangelogXml(project, "user/" + sqlDatabaseName, "user.xml");

    project.addConfig(USER_DATABASE_KEY, sqlDatabaseName);

    // Copy liquibase files
    projectRepository.add(project, SOURCE, "user.xml", getSqlLiquibasePath(sqlDatabaseName));
    projectRepository.add(project, SOURCE, "user.csv", getSqlLiquibasePath(sqlDatabaseName));
    projectRepository.add(project, SOURCE, "user_authority.csv", getSqlLiquibasePath(sqlDatabaseName));
    projectRepository.add(project, SOURCE, "authority.csv", getSqlLiquibasePath(sqlDatabaseName));
  }

  private String getSqlLiquibasePath(String sqlDatabaseName) {
    return getPath(MAIN_RESOURCES, TARGET_RESOURCE + "/" + sqlDatabaseName);
  }
}
