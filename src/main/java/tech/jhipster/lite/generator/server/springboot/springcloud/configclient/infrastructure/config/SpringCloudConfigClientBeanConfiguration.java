package tech.jhipster.lite.generator.server.springboot.springcloud.configclient.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.server.springboot.springcloud.common.domain.SpringCloudCommonService;
import tech.jhipster.lite.generator.server.springboot.springcloud.configclient.domain.SpringCloudConfigClientDomainService;
import tech.jhipster.lite.generator.server.springboot.springcloud.configclient.domain.SpringCloudConfigClientService;

@Configuration
public class SpringCloudConfigClientBeanConfiguration {

  private final BuildToolService buildToolService;
  private final SpringCloudCommonService springCloudCommonService;

  public SpringCloudConfigClientBeanConfiguration(BuildToolService buildToolService, SpringCloudCommonService springCloudCommonService) {
    this.buildToolService = buildToolService;
    this.springCloudCommonService = springCloudCommonService;
  }

  @Bean
  public SpringCloudConfigClientService springCloudConfigClientService() {
    return new SpringCloudConfigClientDomainService(buildToolService, springCloudCommonService);
  }
}
