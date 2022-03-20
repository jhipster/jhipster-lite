package tech.jhipster.lite.generator.server.springboot.broker.kafka.domain;

public class Kafka {

  public static final String KAFKA_DOCKER_IMAGE = "confluentinc/cp-kafka";

  private Kafka() {}

  public static String getKafkaDockerImage() {
    return KAFKA_DOCKER_IMAGE;
  }
}
