package tech.jhipster.lite.generator.server.springboot.broker.kafka.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.docker.domain.DockerImages;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.broker.kafka.domain.KafkaDomainService;
import tech.jhipster.lite.generator.server.springboot.broker.kafka.domain.KafkaService;

@Configuration
public class KafkaBeanConfiguration {

  private final ProjectRepository projectRepository;

  private final DockerImages dockerImages;

  public KafkaBeanConfiguration(final ProjectRepository projectRepository, final DockerImages dockerImages) {
    this.projectRepository = projectRepository;
    this.dockerImages = dockerImages;
  }

  @Bean
  public KafkaService kafkaService() {
    return new KafkaDomainService(projectRepository, dockerImages);
  }
}
