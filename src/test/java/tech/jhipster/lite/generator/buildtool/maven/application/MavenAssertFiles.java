package tech.jhipster.lite.generator.buildtool.maven.application;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPathOf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;
import tech.jhipster.lite.generator.project.domain.Project;

public class MavenAssertFiles {

  public static void assertFilesPomXml(Project project) {
    assertFileExist(project, "pom.xml");
  }

  public static void assertFilesMavenWrapper(Project project) {
    assertFileExist(project, "mvnw");
    assertExecutePermission(project, "mvnw");
    assertFileExist(project, "mvnw.cmd");
    assertExecutePermission(project, "mvnw.cmd");
    assertFileExist(project, ".mvn/wrapper/MavenWrapperDownloader.java");
    assertFileExist(project, ".mvn/wrapper/maven-wrapper.jar");
    assertFileExist(project, ".mvn/wrapper/maven-wrapper.properties");
  }

  private static void assertExecutePermission(Project project, String filename) {
    try {
      Set<PosixFilePermission> posixFilePermissions = Files.getPosixFilePermissions(getPathOf(project.getFolder(), filename));
      assertThat(posixFilePermissions).contains(PosixFilePermission.OWNER_EXECUTE);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  public static void assertFilesMaven(Project project) {
    assertFilesPomXml(project);
    assertFilesMavenWrapper(project);
  }
}
