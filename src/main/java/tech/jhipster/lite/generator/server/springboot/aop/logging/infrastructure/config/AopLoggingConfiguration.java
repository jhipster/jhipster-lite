package tech.jhipster.lite.generator.server.springboot.aop.logging.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.aop.logging.domain.AopLoggingDomainService;
import tech.jhipster.lite.generator.server.springboot.aop.logging.domain.AopLoggingService;
import tech.jhipster.lite.generator.server.springboot.properties.domain.SpringBootPropertiesService;

@Configuration
public class AopLoggingConfiguration {

  public final ProjectRepository projectRepository;
  public final BuildToolService buildToolService;
  public final SpringBootPropertiesService springBootPropertiesService;

  public AopLoggingConfiguration(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootPropertiesService springBootPropertiesService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Bean
  public AopLoggingService aopLoggingService() {
    return new AopLoggingDomainService(projectRepository, buildToolService, springBootPropertiesService);
  }
}
