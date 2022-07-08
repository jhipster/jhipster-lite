package tech.jhipster.lite.generator.server.springboot.broker.kafka.domain;

import tech.jhipster.lite.docker.domain.DockerImages;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

public class KafkaModuleFactory {

  private static final JHipsterSource SOURCE = from("server/springboot/broker/kafka");

  private final DockerImages dockerImages;

  public KafkaModuleFactory(DockerImages dockerImages) {
    this.dockerImages = dockerImages;
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    //@formatter:off
    final JHipsterModuleBuilder builder = moduleBuilder(properties)
      .context()
        .put("zookeeperDockerImage", dockerImages.get("confluentinc/cp-zookeeper").fullName())
        .put("kafkaDockerImage", dockerImages.get("confluentinc/cp-kafka").fullName())
        .and()
      .javaDependencies()
        .dependency(groupId("org.apache.kafka"), artifactId("kafka-clients"))
        .and()
      .files()
        .add(SOURCE.template("kafka.yml"), toSrcMainDocker().append("kafka.yml"))
        .and();
    //@formatter:on

    return builder.build();
  }
}
