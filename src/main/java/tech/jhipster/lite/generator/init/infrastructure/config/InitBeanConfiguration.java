package tech.jhipster.lite.generator.init.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.init.domain.InitDomainService;
import tech.jhipster.lite.generator.init.domain.InitService;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

@Configuration
public class InitBeanConfiguration {

  private final NpmService npmService;
  private final ProjectRepository projectRepository;

  public InitBeanConfiguration(NpmService npmService, ProjectRepository projectRepository) {
    this.npmService = npmService;
    this.projectRepository = projectRepository;
  }

  @Bean
  public InitService initService() {
    return new InitDomainService(npmService, projectRepository);
  }
}
