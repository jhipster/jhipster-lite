package tech.jhipster.lite.module.infrastructure.secondary;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.lite.TestFileUtils.contentNormalizingNewLines;
import static tech.jhipster.lite.TestFileUtils.loadDefaultProperties;
import static tech.jhipster.lite.TestFileUtils.tmpDirForTest;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModulesFixture.allProperties;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;
import tech.jhipster.lite.module.domain.standalonedocker.JHipsterModuleDockerComposeFile;
import tech.jhipster.lite.module.domain.startupcommand.DockerComposeFile;

@UnitTest
public class FileSystemDockerComposeFileHandlerTest {

  public static final Path EXISTING_SPRING_COMPOSE = Path.of(
    "src/test/resources/projects/project-with-root-compose-file/docker-compose.yml"
  );
  public static final String COMPOSE_FILE_NAME = "docker-compose.yml";

  private static final FileSystemDockerComposeFileHandler handler = new FileSystemDockerComposeFileHandler();

  @Test
  void shouldCreateDefaultRootDockerComposeFileForProjectWithoutIntegration() {
    String folder = tmpDirForTest();

    handler.handle(new JHipsterProjectFolder(folder), dockerComposeFile(redisDockerComposeFileReference()));

    assertThat(contentNormalizingNewLines(Path.of(folder, COMPOSE_FILE_NAME))).contains(
      """
      include:
        - src/main/docker/redis.yml
      """
    );
  }

  @Test
  void shouldUpdateRootDockerComposeFile() {
    String folder = tmpDirForTest();
    Path propertiesFile = Path.of(folder, COMPOSE_FILE_NAME);
    loadDefaultProperties(EXISTING_SPRING_COMPOSE, propertiesFile);

    handler.handle(new JHipsterProjectFolder(folder), dockerComposeFile(kafkaDockerComposeFileReference()));

    assertThat(contentNormalizingNewLines(propertiesFile)).contains(
      """
      include:
        - src/main/docker/redis.yml
        - src/main/docker/kafka.yml
      """
    );
  }

  private JHipsterModuleDockerComposeFile dockerComposeFile(DockerComposeFile file) {
    return JHipsterModuleDockerComposeFile.builder(moduleBuilder(allProperties())).append(file).build();
  }

  private DockerComposeFile redisDockerComposeFileReference() {
    return JHipsterModule.dockerComposeFile("src/main/docker/redis.yml");
  }

  private DockerComposeFile kafkaDockerComposeFileReference() {
    return JHipsterModule.dockerComposeFile("src/main/docker/kafka.yml");
  }
}
