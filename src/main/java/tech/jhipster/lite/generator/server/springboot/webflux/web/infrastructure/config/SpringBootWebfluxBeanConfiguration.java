package tech.jhipster.lite.generator.server.springboot.webflux.web.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.webflux.web.domain.SpringBootWebfluxDomainService;
import tech.jhipster.lite.generator.server.springboot.webflux.web.domain.SpringBootWebfluxService;

@Configuration
public class SpringBootWebfluxBeanConfiguration {

  private final BuildToolService buildToolService;
  private final SpringBootCommonService springBootCommonService;

  public SpringBootWebfluxBeanConfiguration(BuildToolService buildToolService, SpringBootCommonService springBootCommonService) {
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
  }

  @Bean
  public SpringBootWebfluxService springBootWebfluxService() {
    return new SpringBootWebfluxDomainService(buildToolService, springBootCommonService);
  }
}
