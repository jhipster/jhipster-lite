package tech.jhipster.lite.generator.server.springboot.logstash.tcp.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.logstash.tcp.domain.LogstashDomainService;
import tech.jhipster.lite.generator.server.springboot.logstash.tcp.domain.LogstashService;
import tech.jhipster.lite.generator.server.springboot.properties.domain.SpringBootPropertiesService;

@Configuration
public class LogstashBeanConfiguration {

  private final BuildToolService buildToolService;
  private final ProjectRepository projectRepository;
  private final SpringBootPropertiesService springBootPropertiesService;

  public LogstashBeanConfiguration(
    BuildToolService buildToolService,
    ProjectRepository projectRepository,
    SpringBootPropertiesService springBootPropertiesService
  ) {
    this.buildToolService = buildToolService;
    this.projectRepository = projectRepository;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Bean
  public LogstashService logstashService() {
    return new LogstashDomainService(buildToolService, projectRepository, springBootPropertiesService);
  }
}
