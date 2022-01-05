package tech.jhipster.lite.generator.packagemanager.npm.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmDomainService;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.CommandRepository;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@Configuration
public class NpmBeanConfiguration {

  private final CommandRepository commandRepository;
  private final ProjectRepository projectRepository;

  public NpmBeanConfiguration(CommandRepository commandRepository, ProjectRepository projectRepository) {
    this.commandRepository = commandRepository;
    this.projectRepository = projectRepository;
  }

  @Bean
  public NpmService npmService() {
    return new NpmDomainService(commandRepository, projectRepository);
  }
}
