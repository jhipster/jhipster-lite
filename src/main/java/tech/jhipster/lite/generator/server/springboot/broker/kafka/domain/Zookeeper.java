package tech.jhipster.lite.generator.server.springboot.broker.kafka.domain;

public class Zookeeper {

  public static final String ZOOKEEPER_DOCKER_IMAGE = "confluentinc/cp-zookeeper";

  private Zookeeper() {}

  public static String getZookeeperDockerImage() {
    return ZOOKEEPER_DOCKER_IMAGE;
  }
}
