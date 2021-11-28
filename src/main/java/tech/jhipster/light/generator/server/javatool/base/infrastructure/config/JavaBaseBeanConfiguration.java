package tech.jhipster.light.generator.server.javatool.base.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.light.generator.project.domain.ProjectRepository;
import tech.jhipster.light.generator.server.javatool.base.domain.JavaBaseDomainService;
import tech.jhipster.light.generator.server.javatool.base.domain.JavaBaseService;

@Configuration
public class JavaBaseBeanConfiguration {

  private final ProjectRepository projectRepository;

  public JavaBaseBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public JavaBaseService javaBaseService() {
    return new JavaBaseDomainService(projectRepository);
  }
}
