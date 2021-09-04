package tech.jhipster.forge;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tech.jhipster.forge.common.domain.FileUtils.exists;
import static tech.jhipster.forge.common.domain.FileUtils.getPath;

import tech.jhipster.forge.common.domain.FileUtils;
import tech.jhipster.forge.common.domain.Project;

public class TestUtils {

  public static void assertFileExist(Project project, String... paths) {
    assertFileExist(getPath(project.getPath(), getPath(paths)));
  }

  public static void assertFileExist(String... paths) {
    assertTrue(exists(getPath(paths)), "The file '" + getPath(paths) + "' does not " + "exist.");
  }

  public static void assertFileNotExist(Project project, String... paths) {
    assertFileNotExist(getPath(project.getPath(), getPath(paths)));
  }

  public static void assertFileNotExist(String... paths) {
    assertFalse(exists(getPath(paths)), "The file '" + getPath(paths) + "' should not exist.");
  }

  public static Project.ProjectBuilder tmpProjectBuilder() {
    return Project.builder().path(FileUtils.tmpDirForTest());
  }

  public static Project tmpProject() {
    return tmpProjectBuilder().build();
  }
}
