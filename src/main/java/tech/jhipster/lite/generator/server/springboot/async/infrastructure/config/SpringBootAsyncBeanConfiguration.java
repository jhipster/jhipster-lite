package tech.jhipster.lite.generator.server.springboot.async.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.async.domain.SpringBootAsyncDomainService;
import tech.jhipster.lite.generator.server.springboot.async.domain.SpringBootAsyncService;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.properties.domain.SpringBootPropertiesService;

@Configuration
public class SpringBootAsyncBeanConfiguration {

  private final ProjectRepository projectRepository;
  private final SpringBootCommonService springBootCommonService;
  private final SpringBootPropertiesService springBootPropertiesService;

  public SpringBootAsyncBeanConfiguration(
    ProjectRepository projectRepository,
    SpringBootCommonService springBootCommonService,
    SpringBootPropertiesService springBootPropertiesService
  ) {
    this.projectRepository = projectRepository;
    this.springBootCommonService = springBootCommonService;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Bean
  public SpringBootAsyncService springBootAsyncService() {
    return new SpringBootAsyncDomainService(projectRepository, springBootCommonService, springBootPropertiesService);
  }
}
