package tech.jhipster.forge.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.springboot.domain.service.MavenDomainService;
import tech.jhipster.forge.springboot.domain.usecase.MavenService;

@Configuration
public class MavenServiceBeanConfiguration {

  private final ProjectRepository projectRepository;

  public MavenServiceBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public MavenService mavenService() {
    return new MavenDomainService(projectRepository);
  }
}
