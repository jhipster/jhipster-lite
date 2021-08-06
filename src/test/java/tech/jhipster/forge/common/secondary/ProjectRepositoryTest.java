package tech.jhipster.forge.common.secondary;

import static org.assertj.core.api.Assertions.*;

import com.github.mustachejava.MustacheNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
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

    assertThat(FileUtils.exists(project.getPath())).isTrue();
  }

  @Test
  void shouldAdd() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();
    repository.create(project);

    repository.add(project, "common", "README.md");

    assertThat(FileUtils.exists(project.getPath() + File.separator + "README.md")).isTrue();
  }

  @Test
  void shouldNotAdd() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();
    repository.create(project);

    assertThatCode(() -> repository.add(project, "common", UUID.randomUUID().toString())).doesNotThrowAnyException();
  }

  @Test
  void shouldTemplate() throws Exception {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();
    repository.create(project);

    repository.template(project, "common", "README.md.mustache");

    assertThat(FileUtils.exists(project.getPath() + File.separator + "README.md")).isTrue();
  }

  @Test
  void shouldNotTemplate() {
    String path = FileUtils.tmpDirForTest();
    Project project = Project.builder().path(path).build();

    assertThatThrownBy(() -> repository.template(project, "common", "README.md.mustache")).isExactlyInstanceOf(FileNotFoundException.class);

    assertThat(FileUtils.exists(project.getPath() + File.separator + "README.md")).isFalse();
  }
}
