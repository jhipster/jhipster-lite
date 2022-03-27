package tech.jhipster.lite.generator.server.springboot.broker.kafka.domain;

public class Akhq {

  public static final String AKHQ_DOCKER_IMAGE = "tchiotludo/akhq";
  public static final String AFHQ_DOCKER_COMPOSE_FILE = "akhq.yml";

  private Akhq() {}

  public static String getAkhqDockerImage() {
    return AKHQ_DOCKER_IMAGE;
  }

  public static String getAkhqDockerComposeFile() {
    return AFHQ_DOCKER_COMPOSE_FILE;
  }
}
