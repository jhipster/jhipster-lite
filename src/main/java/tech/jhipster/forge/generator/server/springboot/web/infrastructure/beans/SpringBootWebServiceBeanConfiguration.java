package tech.jhipster.forge.generator.server.springboot.web.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.forge.generator.server.springboot.properties.domain.SpringBootPropertiesService;
import tech.jhipster.forge.generator.server.springboot.web.domain.SpringBootWebDomainService;
import tech.jhipster.forge.generator.server.springboot.web.domain.SpringBootWebService;

@Configuration
public class SpringBootWebServiceBeanConfiguration {

  public final BuildToolService buildToolService;
  public final SpringBootPropertiesService springBootPropertiesService;

  public SpringBootWebServiceBeanConfiguration(BuildToolService buildToolService, SpringBootPropertiesService springBootPropertiesService) {
    this.buildToolService = buildToolService;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Bean
  public SpringBootWebService springBootWebService() {
    return new SpringBootWebDomainService(buildToolService, springBootPropertiesService);
  }
}
