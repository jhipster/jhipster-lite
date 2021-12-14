package tech.jhipster.lite.generator.server.springboot.database.mysql.domain;

public class MySQL {

  public static final String TESTCONTAINERS_VERSION = "1.16.0";

  public static final String DOCKER_IMAGE_NAME = "mysql:8.0.27";

  private MySQL() {}

  public static String getTestcontainersVersion() {
    return TESTCONTAINERS_VERSION;
  }

  public static String getDockerImageName() {
    return DOCKER_IMAGE_NAME;
  }
}
