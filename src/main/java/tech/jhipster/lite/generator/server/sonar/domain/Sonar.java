package tech.jhipster.lite.generator.server.sonar.domain;

public class Sonar {

  public static final String SONARSOURCE_MAVEN_PLUGIN_VERSION = "3.9.1.2184";
  public static final String SONARQUBE_DOCKER_IMAGE = "sonarqube:9.2.4-community";

  private Sonar() {}

  public static String getMavenPluginVersion() {
    return SONARSOURCE_MAVEN_PLUGIN_VERSION;
  }

  public static String getSonarqubeDockerImage() {
    return SONARQUBE_DOCKER_IMAGE;
  }
}
