package tech.jhipster.lite.module.infrastructure.secondary;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.lite.TestFileUtils.contentNormalizingNewLines;
import static tech.jhipster.lite.TestFileUtils.loadDefaultProperties;
import static tech.jhipster.lite.TestFileUtils.tmpDirForTest;
import static tech.jhipster.lite.module.domain.JHipsterModule.dockerComposeFile;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.startupcommand.DockerComposeFile;

@UnitTest
class DockerComposeFileHandlerTest {

  public static final Path EXISTING_SPRING_COMPOSE = Path.of(
    "src/test/resources/projects/project-with-root-compose-file/docker-compose.yml"
  );
  public static final String COMPOSE_FILE_NAME = "docker-compose.yml";

  @Test
  void shouldCreateUnknownFile() {
    Path rootComposeFile = Path.of(tmpDirForTest(), COMPOSE_FILE_NAME);
    DockerComposeFileHandler handler = new DockerComposeFileHandler(rootComposeFile);

    handler.append(redisDockerComposeFileReference());

    assertThat(contentNormalizingNewLines(rootComposeFile)).isEqualTo(
      """
      include:
        - src/main/docker/redis.yml
      """
    );
  }

  @Test
  void shouldAppendPropertyToDockerComposeFileWithExistingInclude() {
    Path rootComposeFile = Path.of(tmpDirForTest(), COMPOSE_FILE_NAME);
    loadDefaultProperties(EXISTING_SPRING_COMPOSE, rootComposeFile);
    DockerComposeFileHandler handler = new DockerComposeFileHandler(rootComposeFile);

    handler.append(kafkaDockerComposeFileReference());

    assertThat(contentNormalizingNewLines(rootComposeFile)).isEqualTo(
      """
      include:
        - src/main/docker/redis.yml
        - src/main/docker/kafka.yml
      """
    );
  }

  @Test
  void shouldNotAppendExistingDockerComposePath() {
    Path rootComposeFile = Path.of(tmpDirForTest(), COMPOSE_FILE_NAME);
    loadDefaultProperties(EXISTING_SPRING_COMPOSE, rootComposeFile);
    DockerComposeFileHandler handler = new DockerComposeFileHandler(rootComposeFile);

    handler.append(redisDockerComposeFileReference());

    assertThat(contentNormalizingNewLines(rootComposeFile)).isEqualTo(
      """
      include:
        - src/main/docker/redis.yml
      """
    );
  }

  @Test
  void shouldNotAppendDuplicatedDockerComposePath() {
    Path rootComposeFile = Path.of(tmpDirForTest(), "docker-compose.yml");
    loadDefaultProperties(EXISTING_SPRING_COMPOSE, rootComposeFile);
    DockerComposeFileHandler handler = new DockerComposeFileHandler(rootComposeFile);

    handler.append(redisDockerComposeFileReference());
    handler.append(redisDockerComposeFileReference());

    assertThat(contentNormalizingNewLines(rootComposeFile)).isEqualTo(
      """
      include:
        - src/main/docker/redis.yml
      """
    );
  }

  private DockerComposeFile redisDockerComposeFileReference() {
    return dockerComposeFile("src/main/docker/redis.yml");
  }

  private DockerComposeFile kafkaDockerComposeFileReference() {
    return dockerComposeFile("src/main/docker/kafka.yml");
  }
}
