package tech.jhipster.lite.generator.module.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.lite.generator.module.domain.JHipsterModule.*;
import static tech.jhipster.lite.generator.module.domain.JHipsterModulesFixture.*;
import static tech.jhipster.lite.generator.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.JHipsterProjectFolder;

@UnitTest
class FileSystemModulesRepositoryTest {

  private static final FileSystemJHipsterModulesRepository repository = new FileSystemJHipsterModulesRepository();

  @Test
  void shouldNotWriteOnUnwritablePath() {
    JHipsterProjectFolder project = new JHipsterProjectFolder(Paths.get("src/test/resources/generator").toAbsolutePath().toString());

    JHipsterModule module = moduleForProject(project)
      .files()
      .add(from("server/springboot/core/MainApp.java.mustache"), to("content"))
      .and()
      .build();

    assertThatThrownBy(() -> repository.apply(module)).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldApplyModule() {
    assertThatModule(module())
      .createFiles(
        "src/main/java/com/company/myapp/MyApp.java",
        "src/main/java/com/company/myapp/errors/Assert.java",
        "src/main/java/com/company/myapp/errors/AssertionException.java",
        ".gitignore"
      )
      .createFile("src/main/java/com/company/myapp/MyApp.java")
      .containing("com.test.myapp");
  }
}
