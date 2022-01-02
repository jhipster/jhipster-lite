package tech.jhipster.lite.generator.init.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.common.domain.FileUtils.getPathOf;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.project.domain.Project;

public class InitAssertFiles {

  public static void assertFilesPackageJson(Project project) {
    assertFileExist(project, "package.json");
  }

  public static void assertFilesReadme(Project project) {
    assertFileExist(project, "README.md");
  }

  public static void assertFilesConfiguration(Project project) {
    assertFileExist(project, ".gitignore");
    assertFileExist(project, ".gitattributes");
  }

  public static void assertFilesEditorConfiguration(Project project) {
    assertFileExist(project, ".editorconfig");
    assertFileExist(project, ".eslintignore");
  }

  public static void assertFilesPrettier(Project project) {
    assertFileExist(project, ".husky", "pre-commit");
    try {
      Set<PosixFilePermission> posixFilePermissions = Files.getPosixFilePermissions(getPathOf(project.getFolder(), ".husky", "pre-commit"));
      assertThat(posixFilePermissions).contains(PosixFilePermission.OWNER_EXECUTE);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
    assertFileExist(project, ".lintstagedrc.js");
    assertFileExist(project, ".prettierignore");
    assertFileExist(project, ".prettierrc");
  }

  public static void assertFilesInit(Project project) {
    assertFilesPackageJson(project);
    assertFilesReadme(project);
    assertFilesConfiguration(project);
    assertFilesEditorConfiguration(project);
    assertFilesPrettier(project);
  }

  public static void copyNonFormattedFile(Project project) {
    try {
      FileUtils.createFolder(project.getFolder() + "/" + MAIN_JAVA);
      Files.copy(
        getPathOf("src/test/resources/generator/init/TestClass.java"),
        getPathOf(project.getFolder(), MAIN_JAVA, "TestClass.java")
      );
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  public static void assertPrettifiedFile(Project project) {
    try {
      assertEquals(
        FileUtils.read(getPath(project.getFolder(), getPath(MAIN_JAVA, "TestClass.java"))),
        """
        public class TestClass {

          public static void main() {
            System.out.println("Hello Jhipster Lite");
          }
        }
        """
      );
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }
}
