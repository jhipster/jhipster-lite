package tech.jhipster.lite.generator.server.springboot.docker.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.server.sonar.domain.Sonar;

@UnitTest
class SpringBootDockerTest {

  @Test
  void shouldGetDockerBaseImage() {
    assertThat(SpringBootDocker.getDockerBaseImage()).isEqualTo("eclipse-temurin:17-jre-focal");
  }

  @Test
  void shouldGetDockerPlatformArchitecture() {
    assertThat(SpringBootDocker.getDockerPlatformArchitecture()).isEqualTo("amd64");
  }

  @Test
  void shouldGetJibPluginVersion() {
    assertThat(SpringBootDocker.getJibPluginVersion()).isEqualTo("3.1.4");
  }
}
