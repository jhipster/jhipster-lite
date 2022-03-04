package tech.jhipster.lite.generator.server.springboot.user.domain;

import static tech.jhipster.lite.common.domain.FileUtils.*;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;

import tech.jhipster.lite.generator.project.domain.DatabaseType;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class SpringBootUserDomainService implements SpringBootUserService {

  private static final String SOURCE = "server/springboot/user/sqldatabase";
  private static final String TARGET_INFRA_SECOND_JAVA = "user/infrastructure/secondary";
  private static final String TARGET_DOMAIN_JAVA = "user/domain";
  private static final String USER_DATABASE_KEY = "sqlDatabaseName";

  private final ProjectRepository projectRepository;

  public SpringBootUserDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void addUserAndAuthorityEntities(Project project, DatabaseType sqlDatabase) {
    addUserEntity(project, sqlDatabase);
    addAuthorityEntity(project, sqlDatabase);
    addAbstractAuditingEntity(project, sqlDatabase);
  }

  private void addUserEntity(Project project, DatabaseType sqlDatabase) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath(PACKAGE_PATH));
    project.addConfig(USER_DATABASE_KEY, sqlDatabase.id());

    if (isDatabaseWhichNoNeedsSequenceStrategy(sqlDatabase)) {
      projectRepository.template(
        project,
        SOURCE,
        "UserEntity_without_sequence.java",
        getSqlJavaPath(packageNamePath, sqlDatabase),
        "UserEntity.java"
      );
    } else {
      projectRepository.template(project, SOURCE, "UserEntity.java", getSqlJavaPath(packageNamePath, sqlDatabase));
    }
    projectRepository.template(project, SOURCE, "UserJpaRepository.java", getSqlJavaPath(packageNamePath, sqlDatabase));
    projectRepository.template(project, SOURCE, "UserConstants.java", getSqlDomainJavaPath(packageNamePath));

    projectRepository.template(project, SOURCE, "UserEntityTest.java", getSqlJavaTestPath(packageNamePath, sqlDatabase));
    projectRepository.template(project, SOURCE, "UserJpaRepositoryIT.java", getSqlJavaTestPath(packageNamePath, sqlDatabase));
  }

  private void addAuthorityEntity(Project project, DatabaseType sqlDatabase) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath(PACKAGE_PATH));
    project.addConfig(USER_DATABASE_KEY, sqlDatabase.id());

    projectRepository.template(project, SOURCE, "AuthorityEntity.java", getSqlJavaPath(packageNamePath, sqlDatabase));
    projectRepository.template(project, SOURCE, "AuthorityJpaRepository.java", getSqlJavaPath(packageNamePath, sqlDatabase));

    projectRepository.template(project, SOURCE, "AuthorityEntityTest.java", getSqlJavaTestPath(packageNamePath, sqlDatabase));
  }

  private void addAbstractAuditingEntity(Project project, DatabaseType sqlDatabase) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath(PACKAGE_PATH));
    project.addConfig(USER_DATABASE_KEY, sqlDatabase.id());

    projectRepository.template(project, SOURCE, "AbstractAuditingEntity.java", getSqlJavaPath(packageNamePath, sqlDatabase));
  }

  private String getSqlJavaPath(String packageNamePath, DatabaseType sqlDatabase) {
    return getPath(MAIN_JAVA, packageNamePath, TARGET_INFRA_SECOND_JAVA + "/" + sqlDatabase.id());
  }

  private String getSqlDomainJavaPath(String packageNamePath) {
    return getPath(MAIN_JAVA, packageNamePath, TARGET_DOMAIN_JAVA);
  }

  private String getSqlJavaTestPath(String packageNamePath, DatabaseType sqlDatabase) {
    return getPath(TEST_JAVA, packageNamePath, TARGET_INFRA_SECOND_JAVA + "/" + sqlDatabase.id());
  }

  private boolean isMySQLDatabase(DatabaseType databaseType) {
    return databaseType.equals(DatabaseType.MYSQL);
  }

  private boolean isMariaDBDatabase(DatabaseType databaseType) {
    return databaseType.equals(DatabaseType.MARIADB);
  }

  private boolean isDatabaseWhichNoNeedsSequenceStrategy(DatabaseType databaseType) {
    return isMySQLDatabase(databaseType) || isMariaDBDatabase(databaseType);
  }
}
