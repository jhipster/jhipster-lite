package tech.jhipster.lite.module.infrastructure.secondary;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.lite.TestFileUtils.*;

import ch.qos.logback.classic.Level;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tech.jhipster.lite.Logs;
import tech.jhipster.lite.LogsSpy;
import tech.jhipster.lite.LogsSpyExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;
import tech.jhipster.lite.module.domain.startupcommand.JHipsterStartupCommand;
import tech.jhipster.lite.module.domain.startupcommand.JHipsterStartupCommand.DockerStartupCommandLine;
import tech.jhipster.lite.module.domain.startupcommand.JHipsterStartupCommand.GradleStartupCommandLine;
import tech.jhipster.lite.module.domain.startupcommand.JHipsterStartupCommand.MavenStartupCommandLine;
import tech.jhipster.lite.module.domain.startupcommand.JHipsterStartupCommands;

@UnitTest
@ExtendWith(LogsSpyExtension.class)
class FileSystemStartupCommandsReadmeCommandsHandlerTest {

  private static final String EMPTY_MAVEN = "empty-maven";
  private static final String EMPTY_GRADLE = "empty-gradle";
  private static final FileSystemStartupCommandsReadmeCommandsHandler handler = new FileSystemStartupCommandsReadmeCommandsHandler();

  @Logs
  private LogsSpy logs;

  @Test
  void shouldAddMavenCommandToMavenProjectReadme() {
    JHipsterProjectFolder projectFolder = prepareProjectFolderWithReadme(EMPTY_MAVEN);
    JHipsterStartupCommand command = new MavenStartupCommandLine("./mvnw clean verify sonar:sonar");

    handler.handle(projectFolder, new JHipsterStartupCommands(List.of(command)));

    assertThat(content(projectFolder.filePath("README.md"))).contains("./mvnw clean verify sonar:sonar");
  }

  @Test
  void shouldNotAddMavenCommandToMavenProjectWithoutReadme() {
    JHipsterProjectFolder projectFolder = projectFrom("src/test/resources/projects/" + EMPTY_MAVEN);
    JHipsterStartupCommand command = new MavenStartupCommandLine("./mvnw clean verify sonar:sonar");

    handler.handle(projectFolder, new JHipsterStartupCommands(List.of(command)));

    logs.shouldHave(Level.DEBUG, "Can't apply optional replacement: ");
  }

  @Test
  void shouldNotAddMavenCommandToGradleProjectReadme() {
    JHipsterProjectFolder projectFolder = prepareProjectFolderWithReadme(EMPTY_GRADLE);
    JHipsterStartupCommand command = new MavenStartupCommandLine("./mvnw clean verify sonar:sonar");

    handler.handle(projectFolder, new JHipsterStartupCommands(List.of(command)));

    assertThat(content(projectFolder.filePath("README.md"))).doesNotContain("./mvnw clean verify sonar:sonar");
  }

  @Test
  void shouldAddGradleCommandToGradleProjectReadme() {
    JHipsterProjectFolder projectFolder = prepareProjectFolderWithReadme(EMPTY_GRADLE);
    JHipsterStartupCommand command = new GradleStartupCommandLine("./gradlew clean build sonar --info");

    handler.handle(projectFolder, new JHipsterStartupCommands(List.of(command)));

    assertThat(content(projectFolder.filePath("README.md"))).contains("./gradlew clean build sonar --info");
  }

  @Test
  void shouldNotAddGradleCommandToMavenProjectReadme() {
    JHipsterProjectFolder projectFolder = prepareProjectFolderWithReadme(EMPTY_MAVEN);
    JHipsterStartupCommand command = new GradleStartupCommandLine("./gradlew clean build sonar --info");

    handler.handle(projectFolder, new JHipsterStartupCommands(List.of(command)));

    assertThat(content(projectFolder.filePath("README.md"))).doesNotContain("./gradlew clean build sonar --info");
  }

  @Test
  void shouldNotAdddMavenAndGradleCommandToNotMavenOrGradleProjectReadme() {
    JHipsterProjectFolder projectFolder = prepareProjectFolderWithReadme("empty");
    JHipsterStartupCommand mavenCommand = new MavenStartupCommandLine("./mvnw clean verify sonar:sonar");
    JHipsterStartupCommand gradleCommand = new GradleStartupCommandLine("./gradlew clean build sonar --info");

    handler.handle(projectFolder, new JHipsterStartupCommands(List.of(mavenCommand, gradleCommand)));

    assertThat(content(projectFolder.filePath("README.md")))
      .doesNotContain("./mvnw clean verify sonar:sonar")
      .doesNotContain("./gradlew clean build sonar --info");
  }

  @Test
  void shouldAddDockerComposeCommandToMavenProjectReadme() {
    JHipsterProjectFolder projectFolder = prepareProjectFolderWithReadme(EMPTY_MAVEN);
    JHipsterStartupCommand command = new DockerStartupCommandLine("docker compose -f src/main/docker/sonar.yml up -d");

    handler.handle(projectFolder, new JHipsterStartupCommands(List.of(command)));

    assertThat(content(projectFolder.filePath("README.md"))).contains("docker compose -f src/main/docker/sonar.yml up -d");
  }

  @Test
  void shouldAddDockerComposeCommandToGradleProjectReadme() {
    JHipsterProjectFolder projectFolder = prepareProjectFolderWithReadme(EMPTY_GRADLE);
    JHipsterStartupCommand command = new DockerStartupCommandLine("docker compose -f src/main/docker/sonar.yml up -d");

    handler.handle(projectFolder, new JHipsterStartupCommands(List.of(command)));

    assertThat(content(projectFolder.filePath("README.md"))).contains("docker compose -f src/main/docker/sonar.yml up -d");
  }

  @ParameterizedTest
  @MethodSource("commandOrderScenarios")
  void shouldAddCommandsToProjectReadmeRespectingInsertOrder(List<JHipsterStartupCommand> commands, String expectedReadmeContent) {
    JHipsterProjectFolder projectFolder = prepareProjectFolderWithReadme(EMPTY_MAVEN);

    handler.handle(projectFolder, new JHipsterStartupCommands(commands));

    assertThat(content(projectFolder.filePath("README.md"))).contains(expectedReadmeContent);
  }

  private JHipsterProjectFolder prepareProjectFolderWithReadme(String projectType) {
    JHipsterProjectFolder projectFolder = projectFrom("src/test/resources/projects/" + projectType);
    copy(Paths.get("src/test/resources/projects/README.md"), projectFolder.filePath("README.md"));
    return projectFolder;
  }

  private static Stream<Arguments> commandOrderScenarios() {
    JHipsterStartupCommand mavenCommand = new MavenStartupCommandLine("./mvnw clean verify sonar:sonar");
    JHipsterStartupCommand dockerComposeCommand = new DockerStartupCommandLine("docker compose -f src/main/docker/sonar.yml up -d");

    return Stream.of(
      Arguments.of(
        List.of(mavenCommand, dockerComposeCommand),
        """
        ```bash
        ./mvnw clean verify sonar:sonar
        ```

        ```bash
        docker compose -f src/main/docker/sonar.yml up -d
        ```
        """
      ),
      Arguments.of(
        List.of(dockerComposeCommand, mavenCommand),
        """
        ```bash
        docker compose -f src/main/docker/sonar.yml up -d
        ```

        ```bash
        ./mvnw clean verify sonar:sonar
        ```
        """
      )
    );
  }
}
