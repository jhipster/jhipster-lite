package tech.jhipster.lite.generator.server.springboot.broker.kafka.domain;

import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_DOCKER;
import static tech.jhipster.lite.generator.server.springboot.broker.kafka.domain.Akhq.AKHQ_DOCKER_COMPOSE_FILE;
import static tech.jhipster.lite.generator.server.springboot.broker.kafka.domain.Akhq.AKHQ_DOCKER_IMAGE;

import tech.jhipster.lite.docker.domain.DockerImages;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class KafkaDomainService implements KafkaService {

  private static final String SOURCE = "server/springboot/broker/kafka";

  private final ProjectRepository projectRepository;

  private final DockerImages dockerImages;

  public KafkaDomainService(final ProjectRepository projectRepository, final DockerImages dockerImages) {
    this.projectRepository = projectRepository;
    this.dockerImages = dockerImages;
  }

  @Override
  public void addAkhq(final Project project) {
    final String akhqDockerImage = dockerImages.get(AKHQ_DOCKER_IMAGE).fullName();
    project.addConfig("akhqDockerImage", akhqDockerImage);
    projectRepository.template(
      ProjectFile.forProject(project).withSource(SOURCE, AKHQ_DOCKER_COMPOSE_FILE).withDestination(MAIN_DOCKER, AKHQ_DOCKER_COMPOSE_FILE)
    );
  }
}
