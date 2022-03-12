package tech.jhipster.lite.generator.docker.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;

@IntegrationTest
class DockerApplicationServiceIT {

  @Autowired
  DockerApplicationService dockerApplicationService;

  @Test
  void shouldGetImageNameWithVersion() {
    // When
    Optional<String> imageNameWithVersion = dockerApplicationService.getImageNameWithVersion("mysql");

    // Then
    assertThat(imageNameWithVersion).contains("mysql:8.0.28");
  }

  @Test
  void shouldGetImageVersion() {
    // When
    Optional<String> imageVersion = dockerApplicationService.getImageVersion("mysql");

    // Then
    assertThat(imageVersion).contains("8.0.28");
  }
}
