package tech.jhipster.lite.generator.server.springboot.broker.kafka.domain;

import static org.mockito.Mockito.when;
import static tech.jhipster.lite.TestFileUtils.tmpDirForTest;
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

    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(tmpDirForTest()).build();

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
      .createFile("src/main/docker/kafka.yml");
  }
}
