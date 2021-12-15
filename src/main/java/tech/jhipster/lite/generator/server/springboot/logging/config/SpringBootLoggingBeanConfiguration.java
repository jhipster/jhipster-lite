package tech.jhipster.lite.generator.server.springboot.logging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.logging.domain.SpringBootLoggingDomainService;
import tech.jhipster.lite.generator.server.springboot.logging.domain.SpringBootLoggingService;

@Configuration
public class SpringBootLoggingBeanConfiguration {

  private final ProjectRepository projectRepository;

  public SpringBootLoggingBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public SpringBootLoggingService springBootLoggingService() {
    return new SpringBootLoggingDomainService(projectRepository);
  }
}
