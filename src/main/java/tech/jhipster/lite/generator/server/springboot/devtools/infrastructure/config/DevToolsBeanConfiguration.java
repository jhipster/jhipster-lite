package tech.jhipster.lite.generator.server.springboot.devtools.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.devtools.domain.DevToolsDomainService;
import tech.jhipster.lite.generator.server.springboot.devtools.domain.DevToolsService;

@Configuration
public class DevToolsBeanConfiguration {

  private final BuildToolService buildToolService;
  private final SpringBootCommonService springBootCommonService;

  public DevToolsBeanConfiguration(BuildToolService buildToolService, SpringBootCommonService springBootCommonService) {
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
  }

  @Bean
  public DevToolsService devToolsService() {
    return new DevToolsDomainService(buildToolService, springBootCommonService);
  }
}
