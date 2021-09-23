package tech.jhipster.forge;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tech.jhipster.forge.common.utils.FileUtils.*;

import java.io.IOException;
import java.nio.file.Files;
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

  public static void assertFileContent(Project project, String filename, String value) {
    assertTrue(FileUtils.containsInLine(getPath(project.getPath(), filename), value), "The value '" + value + "' was not found");
  }

  public static void assertFileNoContent(Project project, String filename, String value) {
    assertFalse(FileUtils.containsInLine(getPath(project.getPath(), filename), value), "The value '" + value + "' was found");
  }

  public static Project.ProjectBuilder tmpProjectBuilder() {
    return Project.builder().path(FileUtils.tmpDirForTest());
  }

  public static Project tmpProject() {
    return tmpProjectBuilder().build();
  }

  public static Project tmpProjectWithPomXml() throws IOException {
    Project project = tmpProject();
    FileUtils.createFolder(project.getPath());
    Files.copy(getPathOf("src/test/resources/template/maven/pom.xml"), getPathOf(project.getPath(), "pom.xml"));
    return project;
  }
}
