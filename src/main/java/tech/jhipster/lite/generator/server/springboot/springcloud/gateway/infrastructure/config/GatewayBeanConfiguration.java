package tech.jhipster.lite.generator.server.springboot.springcloud.gateway.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.springcloud.common.domain.SpringCloudCommonService;
import tech.jhipster.lite.generator.server.springboot.springcloud.gateway.domain.GatewayDomainService;
import tech.jhipster.lite.generator.server.springboot.springcloud.gateway.domain.GatewayService;

@Configuration
public class GatewayBeanConfiguration {

  private final BuildToolService buildToolService;
  private final ProjectRepository projectRepository;
  private final SpringCloudCommonService springCloudCommonService;

  public GatewayBeanConfiguration(
    BuildToolService buildToolService,
    ProjectRepository projectRepository,
    SpringCloudCommonService springCloudCommonService
  ) {
    this.buildToolService = buildToolService;
    this.projectRepository = projectRepository;
    this.springCloudCommonService = springCloudCommonService;
  }

  @Bean
  public GatewayService gatewayService() {
    return new GatewayDomainService(buildToolService, projectRepository, springCloudCommonService);
  }
}
