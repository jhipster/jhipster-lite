package tech.jhipster.lite.module.domain.standalonedocker;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.lite.module.domain.JHipsterModule.dockerComposeFile;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModulesFixture.allProperties;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class JHipsterModuleDockerComposeFileTest {

  @Test
  void hasSimpleString() {
    JHipsterModuleDockerComposeFile dockerComposeFile = JHipsterModuleDockerComposeFile.builder(moduleBuilder(allProperties()))
      .append(dockerComposeFile("src/main/docker/redis.yml"))
      .append(dockerComposeFile("src/main/docker/kafka.yml"))
      .build();

    assertThat(dockerComposeFile).hasToString(
      "DockerComposeFiles[files=[DockerComposeFile[path=src/main/docker/redis.yml], DockerComposeFile[path=src/main/docker/kafka.yml]]]"
    );
  }
}
