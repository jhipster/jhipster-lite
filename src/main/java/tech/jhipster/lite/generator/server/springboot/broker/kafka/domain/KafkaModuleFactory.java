package tech.jhipster.lite.generator.server.springboot.broker.kafka.domain;

import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVA;
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
        .dependency(groupId("org.testcontainers"), artifactId("kafka"))
        .and()
      .files()
        .add(SOURCE.template("kafka.yml"), toSrcMainDocker().append("kafka.yml"))
        .add(SOURCE.template("KafkaTestContainerExtension.java"), toSrcTestJava().append("/server/springboot/broker/kafka/KafkaTestContainerExtension.java"))
        .add(SOURCE.template("KafkaPropertiesTest.java"), toSrcTestJava().append("/technical/infrastructure/config//kafka/KafkaPropertiesTest.java"))
        .add(SOURCE.template("KafkaProperties.java"), toSrcMainJava().append("/technical/infrastructure/config/kafka/KafkaProperties.java"))
        .add(SOURCE.template("KafkaConfiguration.java"), toSrcMainJava().append("/technical/infrastructure/config/kafka/KafkaConfiguration.java"))
      .and()
      .mandatoryReplacements()
        .in(TEST_JAVA + "/IntegrationTest.java")
          .add(text("import org.springframework.boot.test.context.SpringBootTest;"),
            """
          import org.junit.jupiter.api.extension.ExtendWith;
          import org.springframework.boot.test.context.SpringBootTest;""")
          .add(text("public @interface"),
            """
          @ExtendWith(KafkaTestContainerExtension.class)
          public @interface""")
          .and()
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
