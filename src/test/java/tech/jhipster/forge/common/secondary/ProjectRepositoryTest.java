package tech.jhipster.forge.common.secondary;

import static org.assertj.core.api.Assertions.assertThatCode;
import static tech.jhipster.forge.TestUtils.assertFileExist;
import static tech.jhipster.forge.TestUtils.assertFileNotExist;
import static tech.jhipster.forge.common.domain.FileUtils.getPath;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.FileUtils;
import tech.jhipster.forge.common.domain.Project;

@UnitTest
@ExtendWith(SpringExtension.class)
class ProjectRepositoryTest {

  @InjectMocks
  ProjectRepository repository;

  @Test
  void shouldCreate() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();

    repository.create(project);

    assertFileExist(project.getPath());
  }

  @Test
  void shouldAdd() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();
    repository.create(project);

    repository.add(project, "common", "README.txt");

    assertFileExist(project, "README.txt");
  }

  @Test
  void shouldNotAdd() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();
    repository.create(project);

    assertThatCode(() -> repository.add(project, "common", UUID.randomUUID().toString())).doesNotThrowAnyException();
  }

  @Test
  void shouldAddWithDestination() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();
    repository.create(project);

    repository.add(project, "common", "README.txt", getPath("src", "main", "resources"));

    assertFileExist(project, "src", "main", "resources", "README.txt");
  }

  @Test
  void shouldAddWithDestinationAndDestinationFilename() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();
    repository.create(project);

    repository.add(project, "common", "README.txt", getPath("src", "main", "resources"), "FINAL-README.txt");

    assertFileExist(project, "src", "main", "resources", "FINAL-README.txt");
  }

  @Test
  void shouldTemplate() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();
    repository.create(project);

    repository.template(project, "common", "README.md");

    assertFileExist(project, "README.md");
  }

  @Test
  void shouldTemplateWithExtension() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();
    repository.create(project);

    repository.template(project, "common", "README.md.mustache");

    assertFileExist(project, "README.md");
  }

  @Test
  void shouldNotTemplate() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();

    repository.template(project, "common", "README.md.wrong.mustache");

    assertFileNotExist(project, "README.md");
  }

  @Test
  void shouldTemplateWithDestination() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();
    repository.create(project);

    repository.template(project, "common", "README.md.mustache", getPath("src", "main", "resources"));

    assertFileExist(project, "src", "main", "resources", "README.md");
  }

  @Test
  void shouldTemplateWithDestinationAndDestinationFilename() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();
    repository.create(project);

    repository.template(project, "common", "README.md.mustache", getPath("src", "main", "resources"), "FINAL-README.md");

    assertFileExist(project, "src", "main", "resources", "FINAL-README.md");
  }
}
