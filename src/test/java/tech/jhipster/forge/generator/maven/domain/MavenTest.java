package tech.jhipster.forge.generator.maven.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tech.jhipster.forge.TestUtils.assertFileContent;
import static tech.jhipster.forge.TestUtils.tmpProject;
import static tech.jhipster.forge.common.utils.FileUtils.getPathOf;

import java.io.IOException;
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
    Project project = initProjectWithPomXml();

    assertTrue(Maven.isMavenProject(project));
  }

  @Test
  void shouldAddParent() throws Exception {
    Project project = initProjectWithPomXml();

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
    Project project = initProjectWithPomXml();

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

  @Test
  void shouldAddPlugin() throws Exception {
    Project project = initProjectWithPomXml();

    Plugin plugin = Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin").build();
    Maven.addPlugin(project, plugin);

    assertFileContent(project, "pom.xml", "<groupId>org.springframework.boot</groupId>");
    assertFileContent(project, "pom.xml", "<artifactId>spring-boot-maven-plugin</artifactId>");
  }

  @Test
  void shouldNotAddPluginWhenNoPomXml() throws Exception {
    Project project = tmpProject();
    FileUtils.createFolder(project.getPath());

    Plugin plugin = Plugin.builder().groupId("org.springframework.boot").artifactId("spring-boot-maven-plugin").build();
    assertThatThrownBy(() -> Maven.addPlugin(project, plugin)).isExactlyInstanceOf(GeneratorException.class);
  }

  private Project initProjectWithPomXml() throws IOException {
    Project project = tmpProject();
    FileUtils.createFolder(project.getPath());
    Files.copy(getPathOf("src/test/resources/template/maven/pom.xml"), getPathOf(project.getPath(), "pom.xml"));
    return project;
  }
}
