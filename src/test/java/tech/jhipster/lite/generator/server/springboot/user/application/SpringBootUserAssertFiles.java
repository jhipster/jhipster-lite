package tech.jhipster.lite.generator.server.springboot.user.application;

import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.*;

import tech.jhipster.lite.generator.project.domain.DatabaseType;
import tech.jhipster.lite.generator.project.domain.Project;

public class SpringBootUserAssertFiles {

  public static void assertFilesSqlJavaUser(Project project, DatabaseType databaseType) {
    checkJavaFile(project, "UserEntity.java", databaseType);
    checkJavaFile(project, "UserConstants.java", databaseType);
    checkJavaFile(project, "UserJpaRepository.java", databaseType);

    checkJavaTestFile(project, "UserEntityTest.java", databaseType);
  }

  public static void assertFilesSqlJavaUserAuthority(Project project, DatabaseType databaseType) {
    checkJavaFile(project, "AuthorityEntity.java", databaseType);
    checkJavaFile(project, "AuthorityRepository.java", databaseType);

    checkJavaTestFile(project, "AuthorityEntityTest.java", databaseType);
  }

  public static void assertFilesSqlJavaAuditEntity(Project project, DatabaseType databaseType) {
    checkJavaFile(project, "AbstractAuditingEntity.java", databaseType);
  }

  private static void checkJavaFile(Project project, String javaFileName, DatabaseType databaseType) {
    String userPackage = project.getPackageName().orElse("com.mycompany.myapp") + ".user.infrastructure.secondary." + databaseType.id();
    String userPath = getPath(
      MAIN_JAVA,
      project.getPackageNamePath().orElse("com/mycompany/myapp"),
      "user/infrastructure/secondary",
      databaseType.id()
    );

    assertFileExist(project, getPath(userPath, javaFileName));
    assertFileContent(project, getPath(userPath, javaFileName), "package " + userPackage);
  }

  private static void checkJavaTestFile(Project project, String javaFileName, DatabaseType databaseType) {
    String userPackage = project.getPackageName().orElse("com.mycompany.myapp") + ".user.infrastructure.secondary." + databaseType.id();
    String userPath = getPath(
      TEST_JAVA,
      project.getPackageNamePath().orElse("com/mycompany/myapp"),
      "user/infrastructure/secondary",
      databaseType.id()
    );

    assertFileExist(project, getPath(userPath, javaFileName));
    assertFileContent(project, getPath(userPath, javaFileName), "package " + userPackage);
  }
}
