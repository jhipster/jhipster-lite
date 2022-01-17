package tech.jhipster.lite.generator.server.springboot.docker.domain;

public class SpringBootDocker {

  private static String DOCKER_BASE_IMAGE = "eclipse-temurin:11-jre-focal";
  private static String DOCKER_PLATFORM_ARCHITECTURE = "amd64";
  private static String JIB_PLUGIN_VERSION = "3.1.4";

  public static String getDockerBaseImage() {
    return DOCKER_BASE_IMAGE;
  }

  public static String getDockerPlatformArchitecture() {
    return DOCKER_PLATFORM_ARCHITECTURE;
  }

  public static String getJibPluginVersion() {
    return JIB_PLUGIN_VERSION;
  }
}
