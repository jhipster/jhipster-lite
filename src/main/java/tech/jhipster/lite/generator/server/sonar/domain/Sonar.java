package tech.jhipster.lite.generator.server.sonar.domain;

public class Sonar {

  public static final String SONARQUBE_DOCKER_IMAGE_NAME = "sonarqube";

  private Sonar() {}

  public static String getSonarqubeDockerImageName() {
    return SONARQUBE_DOCKER_IMAGE_NAME;
  }
}
