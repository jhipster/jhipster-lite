package tech.jhipster.lite.generator.server.springboot.springcloud.configclient.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.logging.domain.SpringBootLoggingService;
import tech.jhipster.lite.generator.server.springboot.mvc.web.domain.SpringBootMvcDomainService;
import tech.jhipster.lite.generator.server.springboot.mvc.web.domain.SpringBootMvcService;
import tech.jhipster.lite.generator.server.springboot.properties.domain.SpringBootPropertiesService;
import tech.jhipster.lite.generator.server.springboot.springcloud.configclient.domain.SpringCloudConfigClientDomainService;
import tech.jhipster.lite.generator.server.springboot.springcloud.configclient.domain.SpringCloudConfigClientService;

@Configuration
public class SpringCloudConfigClientBeanConfiguration {

  public final ProjectRepository projectRepository;
  public final BuildToolService buildToolService;
  public final SpringBootPropertiesService springBootPropertiesService;

  public SpringCloudConfigClientBeanConfiguration(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootPropertiesService springBootPropertiesService,
    SpringBootLoggingService springBootLoggingService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Bean
  public SpringCloudConfigClientService springCloudConfigClientService() {
    return new SpringCloudConfigClientDomainService(projectRepository, buildToolService, springBootPropertiesService);
  }
}
