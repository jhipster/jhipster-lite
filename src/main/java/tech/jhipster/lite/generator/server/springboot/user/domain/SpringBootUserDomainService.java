package tech.jhipster.lite.generator.server.springboot.user.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;

import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class SpringBootUserDomainService implements SpringBootUserService {

  public static final String SOURCE = "server/springboot/user/sqldatabase";
  public static final String TARGET_JAVA = "user/infrastructure/secondary";
  public static final String USER_DATABASE_KEY = "sqlDatabaseName";

  private final ProjectRepository projectRepository;

  public SpringBootUserDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void addSqlJavaUser(Project project, String sqlDatabaseName) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));
    project.addConfig(USER_DATABASE_KEY, sqlDatabaseName);

    projectRepository.template(project, SOURCE, "UserEntity.java", getSqlJavaPath(packageNamePath, sqlDatabaseName));
    projectRepository.template(project, SOURCE, "UserConstants.java", getSqlJavaPath(packageNamePath, sqlDatabaseName));
    projectRepository.template(project, SOURCE, "UserJpaRepository.java", getSqlJavaPath(packageNamePath, sqlDatabaseName));

    projectRepository.template(project, SOURCE, "UserEntityTest.java", getSqlJavaTestPath(packageNamePath, sqlDatabaseName));
  }

  @Override
  public void addSqlJavaAuthority(Project project, String sqlDatabaseName) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));
    project.addConfig(USER_DATABASE_KEY, sqlDatabaseName);

    projectRepository.template(project, SOURCE, "AuthorityEntity.java", getSqlJavaPath(packageNamePath, sqlDatabaseName));
    projectRepository.template(project, SOURCE, "AuthorityRepository.java", getSqlJavaPath(packageNamePath, sqlDatabaseName));

    projectRepository.template(project, SOURCE, "AuthorityEntityTest.java", getSqlJavaTestPath(packageNamePath, sqlDatabaseName));
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

  private String getSqlJavaTestPath(String packageNamePath, String sqlDatabaseName) {
    return getPath(TEST_JAVA, packageNamePath, TARGET_JAVA + "/" + sqlDatabaseName);
  }
}
