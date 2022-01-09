package tech.jhipster.lite.generator.server.springboot.user.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.user.domain.SpringBootUserDomainService;
import tech.jhipster.lite.generator.server.springboot.user.domain.SpringBootUserService;

@Configuration
public class SpringBootUserConfiguration {

  private final ProjectRepository projectRepository;

  public SpringBootUserConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public SpringBootUserService springBootUserService() {
    return new SpringBootUserDomainService(projectRepository);
  }
}
