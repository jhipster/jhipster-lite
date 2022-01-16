package tech.jhipster.lite.generator.server.springboot.user.application;

import static tech.jhipster.lite.TestUtils.assertFileContent;
import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.*;

import tech.jhipster.lite.generator.project.domain.Project;

public class SpringBootUserAssertFiles {

  public static void assertFilesSqlJavaUser(Project project, String databaseName) {
    checkJavaFile(project, "UserEntity.java", databaseName);
    checkJavaFile(project, "UserConstants.java", databaseName);
    checkJavaFile(project, "UserJpaRepository.java", databaseName);

    checkJavaTestFile(project, "UserEntityTest.java", databaseName);
  }

  public static void assertFilesSqlJavaUserAuthority(Project project, String databaseName) {
    checkJavaFile(project, "AuthorityEntity.java", databaseName);
    checkJavaFile(project, "AuthorityRepository.java", databaseName);

    checkJavaTestFile(project, "AuthorityEntityTest.java", databaseName);
  }

  public static void assertFilesSqlJavaAuditEntity(Project project, String databaseName) {
    checkJavaFile(project, "AbstractAuditingEntity.java", databaseName);
  }

  private static void checkJavaFile(Project project, String javaFileName, String databaseName) {
    String userPackage = project.getPackageName().orElse("com.mycompany.myapp") + ".user.infrastructure.secondary." + databaseName;
    String userPath = getPath(
      MAIN_JAVA,
      project.getPackageNamePath().orElse("com/mycompany/myapp"),
      "user/infrastructure/secondary",
      databaseName
    );

    assertFileExist(project, getPath(userPath, javaFileName));
    assertFileContent(project, getPath(userPath, javaFileName), "package " + userPackage);
  }

  private static void checkJavaTestFile(Project project, String javaFileName, String databaseName) {
    String userPackage = project.getPackageName().orElse("com.mycompany.myapp") + ".user.infrastructure.secondary." + databaseName;
    String userPath = getPath(
      TEST_JAVA,
      project.getPackageNamePath().orElse("com/mycompany/myapp"),
      "user/infrastructure/secondary",
      databaseName
    );

    assertFileExist(project, getPath(userPath, javaFileName));
    assertFileContent(project, getPath(userPath, javaFileName), "package " + userPackage);
  }
}
