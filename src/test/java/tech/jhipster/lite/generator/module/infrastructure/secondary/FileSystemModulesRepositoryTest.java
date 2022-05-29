package tech.jhipster.lite.generator.module.infrastructure.secondary;

import static tech.jhipster.lite.generator.module.domain.JHipsterModulesFixture.*;
import static tech.jhipster.lite.generator.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import ch.qos.logback.classic.Level;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tech.jhipster.lite.LogSpy;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.JHipsterProjectFolder;

@UnitTest
@ExtendWith(LogSpy.class)
class FileSystemModulesRepositoryTest {

  private final LogSpy logs;

  public FileSystemModulesRepositoryTest(LogSpy logs) {
    this.logs = logs;
  }

  @Test
  void shouldApplyModule() {
    JHipsterModule module = module();
    addPomToproject(module.projectFolder());

    // @formatter:off
    assertThatModule(module)
      .createFiles(
        "src/main/java/com/company/myapp/MyApp.java",
        "src/main/java/com/company/myapp/errors/Assert.java",
        "src/main/java/com/company/myapp/errors/AssertionException.java",
        ".gitignore"
      )
      .createFile("src/main/java/com/company/myapp/MyApp.java")
        .containing("com.test.myapp")
        .and()
      .createFile("pom.xml")
        .containing(
          """
                  <dependency>
                    <groupId>org.junit.jupiter</groupId>
                    <artifactId>junit-jupiter-engine</artifactId>
                    <version>${spring-boot.version}</version>
                    <scope>test</scope>
                    <optional>true</optional>
                  </dependency>
              """
        )
        .containing("<spring-boot.version>")
        .containing("</spring-boot.version>")
        .containing("<artifactId>spring-boot-starter</artifactId>")
        .containing("<artifactId>problem-spring-web</artifactId>")
        .notContaining(
          "    <dependency>\n" +
          "      <groupId>org.assertj</groupId>\n" +
          "      <artifactId>assertj-core</artifactId>\n" +
          "      <version>${assertj.version}</version>\n" +
          "      <scope>test</scope>\n" +
          "    </dependency>"
        )
        .and()
      .createFile("src/main/java/com/company/myapp/errors/Assert.java")
        .containing("Dummy replacement")
        .containing("Another dummy replacement");
    // @formatter:on

    assertPreActions();
    assertPostActions();
  }

  private static void addPomToproject(JHipsterProjectFolder project) {
    Path folder = Paths.get(project.folder());
    try {
      Files.createDirectories(folder);
    } catch (IOException e) {
      throw new AssertionError(e);
    }

    Path pomPath = folder.resolve("pom.xml");
    try {
      Files.copy(Paths.get("src/test/resources/projects/maven/pom.xml"), pomPath);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  private void assertPreActions() {
    logs.assertLogged(Level.DEBUG, "Applying fixture module");
    logs.assertLogged(Level.DEBUG, "You shouldn't add this by default in your modules :D");
  }

  private void assertPostActions() {
    logs.assertLogged(Level.DEBUG, "Fixture module applied");
    logs.assertLogged(Level.DEBUG, "Applied on");
    logs.assertLogged(Level.DEBUG, System.getProperty("java.io.tmpdir"));
  }
}
