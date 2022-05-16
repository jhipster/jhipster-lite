package tech.jhipster.lite.generator.server.springboot.broker.pulsar.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;
import static tech.jhipster.lite.generator.server.springboot.broker.pulsar.domain.Pulsar.PULSAR_DOCKER_COMPOSE_FILE;
import static tech.jhipster.lite.generator.server.springboot.broker.pulsar.domain.Pulsar.PULSAR_DOCKER_IMAGE;

import java.util.TreeMap;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.docker.domain.DockerService;
import tech.jhipster.lite.generator.project.domain.DefaultConfig;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

public class PulsarDomainService implements PulsarService {

  private static final String SOURCE = "server/springboot/broker/pulsar";

  private final BuildToolService buildToolService;

  private final ProjectRepository projectRepository;

  private final SpringBootCommonService springBootCommonService;

  private final DockerService dockerService;

  public PulsarDomainService(
    final BuildToolService buildToolService,
    final ProjectRepository projectRepository,
    final SpringBootCommonService springBootCommonService,
    DockerService dockerService
  ) {
    this.buildToolService = buildToolService;
    this.projectRepository = projectRepository;
    this.springBootCommonService = springBootCommonService;
    this.dockerService = dockerService;
  }

  @Override
  public void init(final Project project) {
    addApachePulsarClient(project);
    addDockerCompose(project);
    addProperties(project);
    addTestcontainers(project);
    addConfiguration(project);
  }

  private void addConfiguration(final Project project) {
    final String packageNamePath = project.getPackageNamePath().orElse(getPath(DefaultConfig.PACKAGE_PATH));
    final String configPulsarPath = TECHNICAL_INFRASTRUCTURE_CONFIG + "/pulsar";

    projectRepository.template(project, SOURCE, "PulsarProperties.java", getPath(MAIN_JAVA, packageNamePath, configPulsarPath));
    projectRepository.template(project, SOURCE, "PulsarConfiguration.java", getPath(MAIN_JAVA, packageNamePath, configPulsarPath));
    projectRepository.template(project, SOURCE, "PulsarConfigurationIT.java", getPath(TEST_JAVA, packageNamePath, configPulsarPath));
  }

  private void addApachePulsarClient(final Project project) {
    final Dependency dependency = Dependency.builder().groupId("org.apache.pulsar").artifactId("pulsar-client").version("2.10.0").build();
    buildToolService.addDependency(project, dependency);
  }

  private void addDockerCompose(final Project project) {
    final String pulsarDockerImage = dockerService.getImageNameWithVersion(PULSAR_DOCKER_IMAGE).orElseThrow();

    project.addDefaultConfig(BASE_NAME);
    project.addConfig("pulsarDockerImage", pulsarDockerImage);
    projectRepository.template(project, SOURCE, PULSAR_DOCKER_COMPOSE_FILE, MAIN_DOCKER, PULSAR_DOCKER_COMPOSE_FILE);
  }

  private void addProperties(final Project project) {
    getPulsarCommonProperties()
      .forEach((k, v) -> {
        springBootCommonService.addProperties(project, k, v);
        springBootCommonService.addPropertiesTest(project, k, v);
      });
    springBootCommonService.addProperties(project, "pulsar.client.service-url", "pulsar://localhost:6650");
    springBootCommonService.addPropertiesTest(project, "pulsar.client.num-io-threads", 8);
    springBootCommonService.addPropertiesTest(project, "pulsar.producer.topic-name", "test-topic");
    springBootCommonService.addPropertiesTest(project, "pulsar.consumer.topic-names[0]", "test-topic");
    springBootCommonService.addPropertiesTest(project, "pulsar.consumer.subscription-name", "test-subscription");
  }

  private TreeMap<String, Object> getPulsarCommonProperties() {
    final TreeMap<String, Object> result = new TreeMap<>();

    result.put("# Pulsar Configuration", "");
    return result;
  }

  void addTestcontainers(final Project project) {
    final String packageNamePath = project.getPackageNamePath().orElse(getPath(DefaultConfig.PACKAGE_PATH));
    projectRepository.template(project, SOURCE, "PulsarTestContainerExtension.java", getPath(TEST_JAVA, packageNamePath));
    this.buildToolService.getVersion(project, "testcontainers")
      .ifPresentOrElse(
        version -> {
          Dependency dependency = Dependency
            .builder()
            .groupId("org.testcontainers")
            .artifactId("pulsar")
            .version("\\${testcontainers.version}")
            .scope("test")
            .build();
          buildToolService.addProperty(project, "testcontainers.version", version);
          buildToolService.addDependency(project, dependency);
        },
        () -> {
          throw new GeneratorException("Testcontainers version not found");
        }
      );
    springBootCommonService.updateIntegrationTestAnnotation(project, "PulsarTestContainerExtension");
  }
}
