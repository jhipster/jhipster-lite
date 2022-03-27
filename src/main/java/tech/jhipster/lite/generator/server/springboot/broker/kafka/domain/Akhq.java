package tech.jhipster.lite.generator.server.springboot.broker.kafka.domain;

public class Akhq {

  public static final String AKHQ_DOCKER_IMAGE = "tchiotludo/akhq";

  private Akhq() {}

  public static String getAkhqDockerImage() {
    return AKHQ_DOCKER_IMAGE;
  }
}
