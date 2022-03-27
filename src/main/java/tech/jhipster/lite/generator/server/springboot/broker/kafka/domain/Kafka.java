package tech.jhipster.lite.generator.server.springboot.broker.kafka.domain;

public class Kafka {

  public static final String KAFKA_DOCKER_IMAGE = "confluentinc/cp-kafka";
  public static final String KAFKA_DOCKER_COMPOSE_FILE = "kafka.yml";

  private Kafka() {}

  public static String getKafkaDockerImage() {
    return KAFKA_DOCKER_IMAGE;
  }

  public static String getKafkaDockerComposeFile() {
    return KAFKA_DOCKER_COMPOSE_FILE;
  }
}
