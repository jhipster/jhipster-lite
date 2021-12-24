package tech.jhipster.lite.generator.server.springboot.devtools.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.server.springboot.devtools.domain.DevToolsDomainService;
import tech.jhipster.lite.generator.server.springboot.devtools.domain.DevToolsService;
import tech.jhipster.lite.generator.server.springboot.properties.domain.SpringBootPropertiesService;

@Configuration
public class DevToolsBeanConfiguration {

  public final BuildToolService buildToolService;
  public final SpringBootPropertiesService springBootPropertiesService;

  public DevToolsBeanConfiguration(BuildToolService buildToolService, SpringBootPropertiesService springBootPropertiesService) {
    this.buildToolService = buildToolService;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Bean
  public DevToolsService devToolsService() {
    return new DevToolsDomainService(buildToolService, springBootPropertiesService);
  }
}
