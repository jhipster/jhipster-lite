package tech.jhipster.lite.generator.buildtool.gradle.application;

import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.project.domain.Project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPathOf;
import static tech.jhipster.lite.generator.project.domain.Constants.BUILD_GRADLE_KTS;
import static tech.jhipster.lite.generator.project.domain.Constants.SETTINGS_GRADLE_KTS;

public class GradleAssertFiles {

  public static void assertFilesBuildGradleKts(Project project) {
    assertFileExist(project, BUILD_GRADLE_KTS);
    assertFileExist(project, SETTINGS_GRADLE_KTS);
  }

  public static void assertFilesGradleWrapper(Project project) {
    assertFileExist(project, "gradlew");
    assertExecutePermission(project, "gradlew");
    assertFileExist(project, "gradlew.bat");
    assertExecutePermission(project, "gradlew.bat");
    assertFileExist(project, "gradle/wrapper/gradle-wrapper.jar");
    assertFileExist(project, "gradle/wrapper/gradle-wrapper.properties");
  }

  private static void assertExecutePermission(Project project, String filename) {
    if (!FileUtils.isPosix()) {
      return;
    }
    try {
      Set<PosixFilePermission> posixFilePermissions = Files.getPosixFilePermissions(getPathOf(project.getFolder(), filename));
      assertThat(posixFilePermissions).contains(PosixFilePermission.OWNER_EXECUTE);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  public static void assertFilesGradle(Project project) {
    assertFilesBuildGradleKts(project);
    assertFilesGradleWrapper(project);
  }
}
