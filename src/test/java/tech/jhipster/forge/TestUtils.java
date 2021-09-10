package tech.jhipster.forge;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tech.jhipster.forge.common.utils.FileUtils.exists;
import static tech.jhipster.forge.common.utils.FileUtils.getPath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.utils.FileUtils;

public class TestUtils {

  private static final Logger log = LoggerFactory.getLogger(TestUtils.class);

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

  public static void assertFileContent(String filename, String value) {
    assertTrue(FileUtils.contains(filename, value), "The value '" + value + "' was not found");
  }

  public static Project.ProjectBuilder tmpProjectBuilder() {
    return Project.builder().path(FileUtils.tmpDirForTest());
  }

  public static Project tmpProject() {
    return tmpProjectBuilder().build();
  }
}
