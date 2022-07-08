package tech.jhipster.lite.generator.server.springboot.broker.kafka.domain;

import static org.mockito.Mockito.when;
import static tech.jhipster.lite.TestFileUtils.tmpDirForTest;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_DOCKER;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.pomFile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.docker.domain.DockerImage;
import tech.jhipster.lite.docker.domain.DockerImages;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
@ExtendWith(MockitoExtension.class)
class KafkaModuleFactoryTest {

  @Mock
  private DockerImages dockerImages;

  @InjectMocks
  private KafkaModuleFactory factory;

  @Test
  void shouldBuildKafkaModule() {
    when(dockerImages.get("confluentinc/cp-zookeeper")).thenReturn(new DockerImage("confluentinc/cp-zookeeper", "1.0.0"));
    when(dockerImages.get("confluentinc/cp-kafka")).thenReturn(new DockerImage("confluentinc/cp-kafka", "1.0.0"));

    JHipsterModuleProperties properties = JHipsterModulesFixture
      .propertiesBuilder(tmpDirForTest())
      .basePackage("com.jhipster.test")
      .projectBaseName("myapp")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, pomFile())
      .createFile("pom.xml")
      .containing(
        """
                <dependency>
                  <groupId>org.apache.kafka</groupId>
                  <artifactId>kafka-clients</artifactId>
                </dependency>
            """
      )
      .and()
      .createFile(MAIN_DOCKER + "/kafka.yml")
      .and()
      .createFile(MAIN_RESOURCES + "/config/application.properties")
      .containing("kafka.bootstrap-servers=localhost:9092")
      .containing("kafka.consumer.key.deserializer=org.apache.kafka.common.serialization.StringDeserializer")
      .containing("kafka.consumer.value.deserializer=org.apache.kafka.common.serialization.StringDeserializer")
      .containing("kafka.consumer.group.id=myapp")
      .containing("kafka.consumer.auto.offset.reset=earliest")
      .containing("kafka.producer.key.serializer=org.apache.kafka.common.serialization.StringSerializer")
      .containing("kafka.producer.value.serializer=org.apache.kafka.common.serialization.StringSerializer")
      .containing("kafka.polling.timeout=10000")
      .and();
  }
}
