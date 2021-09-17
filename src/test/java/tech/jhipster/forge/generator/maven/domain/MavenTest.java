package tech.jhipster.forge.generator.maven.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tech.jhipster.forge.TestUtils.assertFileContent;
import static tech.jhipster.forge.TestUtils.tmpProject;
import static tech.jhipster.forge.common.utils.FileUtils.getPath;
import static tech.jhipster.forge.common.utils.FileUtils.getPathOf;

import java.nio.file.Files;
import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.utils.FileUtils;
import tech.jhipster.forge.error.domain.GeneratorException;

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

  @Test
  void shouldAddParent() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(project.getPath());
    Files.copy(getPathOf("src/test/resources/template/maven/pom.xml"), getPathOf(project.getPath(), "pom.xml"));

    Parent parent = Parent.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-parent").version("2.5.3").build();
    Maven.addParent(project, parent);

    assertFileContent(project, "pom.xml", "<parent>");
    assertFileContent(project, "pom.xml", "<groupId>org.springframework.boot</groupId>");
    assertFileContent(project, "pom.xml", "<artifactId>spring-boot-starter-parent</artifactId>");
    assertFileContent(project, "pom.xml", "<version>2.5.3</version>");
    assertFileContent(project, "pom.xml", "</parent>");
  }

  @Test
  void shouldNotAddParentWhenNoPomXml() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(project.getPath());

    Parent parent = Parent.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-parent").version("2.5.3").build();
    assertThatThrownBy(() -> Maven.addParent(project, parent)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldAddDependency() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(project.getPath());
    Files.copy(getPathOf("src/test/resources/template/maven/pom.xml"), getPathOf(project.getPath(), "pom.xml"));

    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter").build();
    Maven.addDependency(project, dependency);

    assertFileContent(project, "pom.xml", "<groupId>org.springframework.boot</groupId>");
    assertFileContent(project, "pom.xml", "<artifactId>spring-boot-starter</artifactId>");
  }

  @Test
  void shouldNotAddDependencyWhenNoPomXml() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(project.getPath());

    Dependency dependency = Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter").build();
    assertThatThrownBy(() -> Maven.addDependency(project, dependency)).isExactlyInstanceOf(GeneratorException.class);
  }
}
