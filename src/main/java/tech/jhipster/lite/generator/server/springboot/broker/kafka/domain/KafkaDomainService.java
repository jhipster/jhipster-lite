package tech.jhipster.lite.generator.server.springboot.broker.kafka.domain;

import tech.jhipster.lite.docker.domain.DockerImages;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.DefaultConfig;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

import java.util.TreeMap;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.lite.generator.server.springboot.broker.kafka.domain.Akhq.AKHQ_DOCKER_COMPOSE_FILE;
import static tech.jhipster.lite.generator.server.springboot.broker.kafka.domain.Akhq.AKHQ_DOCKER_IMAGE;

public class KafkaDomainService implements KafkaService {

  private static final String SOURCE = "server/springboot/broker/kafka";
  private static final String DUMMY_TOPIC_NAME = "kafka.topic.dummy";
  private static final String DUMMY_PRODUCER_PATH = "dummy/infrastructure/secondary/kafka/producer";
  private static final String DUMMY_CONSUMER_PATH = "dummy/infrastructure/primary/kafka/consumer";

  private final BuildToolService buildToolService;

  private final ProjectRepository projectRepository;

  private final SpringBootCommonService springBootCommonService;

  private final DockerImages dockerImages;

  public KafkaDomainService(
    final BuildToolService buildToolService,
    final ProjectRepository projectRepository,
    final SpringBootCommonService springBootCommonService,
    final DockerImages dockerImages
  ) {
    this.buildToolService = buildToolService;
    this.projectRepository = projectRepository;
    this.springBootCommonService = springBootCommonService;
    this.dockerImages = dockerImages;
  }

  @Override
  public void addDummyProducerConsumer(final Project project) {
    if (springBootCommonService.getProperty(project, DUMMY_TOPIC_NAME).isEmpty()) {
      project.addDefaultConfig(PACKAGE_NAME);
      project.addDefaultConfig(BASE_NAME);
      final String packageNamePath = project.getPackageNamePath().orElse(getPath(DefaultConfig.PACKAGE_PATH));

      final String topicName = "queue." + project.getBaseName().orElse("jhipster") + ".dummy";
      springBootCommonService.addProperties(project, DUMMY_TOPIC_NAME, topicName);
      springBootCommonService.addPropertiesTest(project, DUMMY_TOPIC_NAME, topicName);

      projectRepository.template(
        ProjectFile
          .forProject(project)
          .withSource(SOURCE, "DummyProducer.java")
          .withDestinationFolder(getPath(MAIN_JAVA, packageNamePath, DUMMY_PRODUCER_PATH))
      );
      projectRepository.template(
        ProjectFile
          .forProject(project)
          .withSource(SOURCE, "DummyProducerTest.java")
          .withDestinationFolder(getPath(TEST_JAVA, packageNamePath, DUMMY_PRODUCER_PATH))
      );

      projectRepository.template(
        ProjectFile
          .forProject(project)
          .withSource(SOURCE, "DummyProducerIT.java")
          .withDestinationFolder(getPath(TEST_JAVA, packageNamePath, DUMMY_PRODUCER_PATH))
      );

      projectRepository.template(
        ProjectFile
          .forProject(project)
          .withSource(SOURCE, "AbstractConsumer.java")
          .withDestinationFolder(getPath(MAIN_JAVA, packageNamePath, DUMMY_CONSUMER_PATH))
      );
      projectRepository.template(
        ProjectFile
          .forProject(project)
          .withSource(SOURCE, "DummyConsumer.java")
          .withDestinationFolder(getPath(MAIN_JAVA, packageNamePath, DUMMY_CONSUMER_PATH))
      );
      projectRepository.template(
        ProjectFile
          .forProject(project)
          .withSource(SOURCE, "DummyConsumerIT.java")
          .withDestinationFolder(getPath(TEST_JAVA, packageNamePath, DUMMY_CONSUMER_PATH))
      );
      projectRepository.template(
        ProjectFile
          .forProject(project)
          .withSource(SOURCE, "DummyConsumerTest.java")
          .withDestinationFolder(getPath(TEST_JAVA, packageNamePath, DUMMY_CONSUMER_PATH))
      );
    }
  }

  @Override
  public void addAkhq(final Project project) {
    final String akhqDockerImage = dockerImages.get(AKHQ_DOCKER_IMAGE).fullName();
    project.addConfig("akhqDockerImage", akhqDockerImage);
    projectRepository.template(
      ProjectFile.forProject(project).withSource(SOURCE, AKHQ_DOCKER_COMPOSE_FILE).withDestination(MAIN_DOCKER, AKHQ_DOCKER_COMPOSE_FILE)
    );
  }

  private TreeMap<String, Object> getKafkaCommonProperties(final String kebabBaseName) {
    final TreeMap<String, Object> result = new TreeMap<>();

    result.put("# Kafka Configuration", "");
    result.put("kafka.bootstrap-servers", "localhost:9092");
    result.put("kafka.consumer.'[key.deserializer]'", "org.apache.kafka.common.serialization.StringDeserializer");
    result.put("kafka.consumer.'[value.deserializer]'", "org.apache.kafka.common.serialization.StringDeserializer");
    result.put("kafka.consumer.'[group.id]'", kebabBaseName);
    result.put("kafka.consumer.'[auto.offset.reset]'", "earliest");
    result.put("kafka.producer.'[key.serializer]'", "org.apache.kafka.common.serialization.StringSerializer");
    result.put("kafka.producer.'[value.serializer]'", "org.apache.kafka.common.serialization.StringSerializer");
    result.put("kafka.polling.timeout", "10000");

    return result;
  }
}
