package tech.jhipster.forge.generator.maven.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tech.jhipster.forge.TestUtils.tmpProject;
import static tech.jhipster.forge.common.utils.FileUtils.getPath;

import java.nio.file.Files;
import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.utils.FileUtils;

@UnitTest
public class MavenTest {

  @Test
  void shouldNotBeMavenProject() {
    Project project = tmpProject();

    assertFalse(Maven.isMavenProject(project));
  }

  @Test
  void shouldBeMavenProject() throws Exception {
    Project project = tmpProject();
    String destinationFile = getPath(project.getPath(), "pom.xml");
    FileUtils.createFolder(project.getPath());
    Files.createFile(FileUtils.getPathOf(destinationFile));

    assertTrue(Maven.isMavenProject(project));
  }
}
