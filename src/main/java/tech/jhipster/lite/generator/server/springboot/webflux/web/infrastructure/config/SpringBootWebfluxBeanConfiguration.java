package tech.jhipster.lite.generator.server.springboot.webflux.web.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.server.springboot.webflux.web.domain.SpringBootWebfluxDomainService;
import tech.jhipster.lite.generator.server.springboot.webflux.web.domain.SpringBootWebfluxService;

@Configuration
public class SpringBootWebfluxBeanConfiguration {

  private final BuildToolService buildToolService;

  public SpringBootWebfluxBeanConfiguration(BuildToolService buildToolService) {
    this.buildToolService = buildToolService;
  }

  @Bean
  public SpringBootWebfluxService springBootWebfluxService() {
    return new SpringBootWebfluxDomainService(buildToolService);
  }
}
