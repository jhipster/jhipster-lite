package tech.jhipster.lite.generator.server.springboot.springcloud.configclient.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.springcloud.configclient.domain.SpringCloudConfigClientDomainService;
import tech.jhipster.lite.generator.server.springboot.springcloud.configclient.domain.SpringCloudConfigClientService;

@Configuration
public class SpringCloudConfigClientBeanConfiguration {

  public final ProjectRepository projectRepository;
  public final BuildToolService buildToolService;

  public SpringCloudConfigClientBeanConfiguration(ProjectRepository projectRepository, BuildToolService buildToolService) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
  }

  @Bean
  public SpringCloudConfigClientService springCloudConfigClientService() {
    return new SpringCloudConfigClientDomainService(projectRepository, buildToolService);
  }
}
