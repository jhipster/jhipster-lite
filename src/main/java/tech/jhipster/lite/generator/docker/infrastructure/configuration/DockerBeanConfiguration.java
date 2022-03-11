package tech.jhipster.lite.generator.docker.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.docker.domain.DockerDomainService;
import tech.jhipster.lite.generator.docker.domain.DockerService;

@Configuration
public class DockerBeanConfiguration {

  @Bean
  public DockerService dockerService() {
    return new DockerDomainService();
  }
}
