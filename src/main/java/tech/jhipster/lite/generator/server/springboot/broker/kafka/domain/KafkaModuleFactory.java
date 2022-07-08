package tech.jhipster.lite.generator.server.springboot.broker.kafka.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.docker.domain.DockerImages;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

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
        .and()
      .springMainProperties()
        .set(propertyKey("kafka.bootstrap-servers"), propertyValue("localhost:9092"))
        .set(propertyKey("kafka.consumer.key.deserializer"), propertyValue("org.apache.kafka.common.serialization.StringDeserializer"))
        .set(propertyKey("kafka.consumer.value.deserializer"), propertyValue("org.apache.kafka.common.serialization.StringDeserializer"))
        .set(propertyKey("kafka.consumer.group.id"), propertyValue("myapp"))
        .set(propertyKey("kafka.consumer.auto.offset.reset"), propertyValue("earliest"))
        .set(propertyKey("kafka.producer.key.serializer"), propertyValue("org.apache.kafka.common.serialization.StringSerializer"))
        .set(propertyKey("kafka.producer.value.serializer"), propertyValue("org.apache.kafka.common.serialization.StringSerializer"))
        .set(propertyKey("kafka.polling.timeout"), propertyValue("10000"))
        .and();
    //@formatter:on

    return builder.build();
  }
}
