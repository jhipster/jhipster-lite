package tech.jhipster.lite.generator.project.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.common.domain.FileUtils.*;
import static tech.jhipster.lite.generator.project.domain.Constants.*;

import com.github.mustachejava.MustacheNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.error.domain.MissingMandatoryValueException;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;

@UnitTest
@ExtendWith(SpringExtension.class)
class ProjectLocalRepositoryTest {

  private static final String POM_XML = "pom.xml";

  @InjectMocks
  private ProjectLocalRepository repository;

  @Test
  void shouldNotAddFileToUnknownFolder() {
    ProjectFile file = ProjectFile.forProject(tmpProject()).withSource("common", UUID.randomUUID().toString()).withSameDestination();

    assertThatThrownBy(() -> repository.add(file)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldAddFiles() {
    Project project = tmpProject();

    Collection<ProjectFile> files = ProjectFile.forProject(project).all("init", "gitattributes", "gitignore");
    repository.add(files);

    assertFileExist(project, "gitattributes");
    assertFileExist(project, "gitignore");
  }

  @Test
  void shouldNotTemplateForUnknownFile() {
    Project project = tmpProject();
    List<ProjectFile> files = List.of(
      ProjectFile.forProject(project).withSource("mustache", "README.md.wrong.mustache").withSameDestination()
    );

    assertThatThrownBy(() -> repository.template(files)).isInstanceOf(MustacheNotFoundException.class);
  }

  @Test
  void shouldTemplatizeFile() {
    Project project = tmpProject();
    List<ProjectFile> files = List.of(ProjectFile.forProject(project).withSource("mustache", README_MD).withSameDestination());

    repository.template(files);

    assertFileExist(project, README_MD);
  }

  @Test
  void shouldTemplatizeFileWithMustacheExtension() {
    Project project = tmpProject();
    List<ProjectFile> files = List.of(ProjectFile.forProject(project).withSource("mustache", "README.md.mustache").withSameDestination());

    repository.template(files);

    assertFileExist(project, README_MD);
  }

  @Test
  void shouldTemplateWithDestinationAndDestinationFilename() {
    Project project = tmpProject();

    repository.template(
      List.of(
        ProjectFile
          .forProject(project)
          .withSource("mustache", "README.md.mustache")
          .withDestination(getPath("src/main/resources"), "FINAL-README.md")
      )
    );

    assertFileExist(project, "src/main/resources", "FINAL-README.md");
  }

  @Test
  void shouldContainsRegexp() {
    Project project = tmpProjectWithPomXml();

    boolean result = repository.containsRegexp(project, ".", POM_XML, "<artifactId>jhipster</artifactId>");

    assertThat(result).isTrue();
  }

  @Test
  void shouldNotContainsRegexp() {
    Project project = tmpProjectWithPomXml();

    boolean result = repository.containsRegexp(project, ".", POM_XML, "<artifactId>chips</artifactId>");

    assertThat(result).isFalse();
  }

  @Test
  void shouldNotContainsRegexpWithoutExistingFile() {
    Project project = tmpProject();

    assertThatThrownBy(() -> repository.containsRegexp(project, ".", POM_XML, "<artifactId>chips</artifactId>"))
      .isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldReplaceText() {
    Project project = tmpProjectWithPomXml();

    String oldText = """
        <name>jhipster</name>
          <description>JHipster Project</description>""";
    String newText = """
        <name>chips</name>
          <description>Chips Project</description>""";

    repository.replaceText(project, "", POM_XML, oldText, newText);

    assertFileContent(project, POM_XML, List.of("<name>chips</name>", "<description>Chips Project</description>"));
  }

  @Test
  void shouldNotReplaceText() {
    Project project = tmpProject();
    String oldText = """
        <name>jhipster</name>
          <description>JHipster Project</description>""";
    String newText = """
        <name>chips</name>
          <description>Chips Project</description>""";

    assertThatThrownBy(() -> repository.replaceText(project, "", POM_XML, oldText, newText)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldWrite() {
    Project project = tmpProject();

    repository.write(project, "hello world", "hello", "hello.world");

    assertFileExist(project, "hello/hello.world");
    assertFileContent(project, "hello/hello.world", "hello world");
  }

  @Test
  void shouldNotWriteWhenDestinationCantBeCreated() {
    Project project = tmpProject();
    repository.write(project, "hello world", ".", "hello");

    assertThatThrownBy(() -> repository.write(project, "another hello world", "hello", "hello.world"))
      .isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldNotWriteNullText() {
    Project project = tmpProject();

    assertThatThrownBy(() -> repository.write(project, null, null, null))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("text");
  }

  @Test
  void shouldRename() {
    Project project = tmpProjectWithPomXml();

    repository.rename(project, ".", POM_XML, "pom.xml.beer");

    assertFileNotExist(project, POM_XML);
    assertFileExist(project, "pom.xml.beer");
  }

  @Test
  void shouldNotRename() {
    Project project = tmpProject();

    assertThatThrownBy(() -> repository.rename(project, ".", POM_XML, "pom.xml.burger")).isExactlyInstanceOf(GeneratorException.class);

    assertFileNotExist(project, POM_XML);
  }
}
