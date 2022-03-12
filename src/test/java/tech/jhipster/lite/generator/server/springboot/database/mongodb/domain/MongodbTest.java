package tech.jhipster.lite.generator.server.springboot.database.mongodb.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

@UnitTest
class MongodbTest {

  @Test
  void shouldGetDockerImageName() {
    assertThat(Mongodb.getMongodbDockerImageName()).isEqualTo("mongo");
  }

  @Test
  void shouldGetMongodbDockerImage() {
    assertThat(Mongodb.getMongodbDockerImageName()).isEqualTo(Mongodb.MONGODB_DOCKER_IMAGE_NAME);
  }

  @Test
  void shouldMongodbDriver() {
    Dependency dependency = Mongodb.mongodbDriver();

    assertThat(dependency.getGroupId()).isEqualTo("org.mongodb");
    assertThat(dependency.getArtifactId()).isEqualTo("mongodb-driver-sync");
  }
}
