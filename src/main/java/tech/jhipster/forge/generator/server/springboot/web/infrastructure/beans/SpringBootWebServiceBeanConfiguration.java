package tech.jhipster.forge.generator.server.springboot.web.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.project.domain.BuildToolRepository;
import tech.jhipster.forge.generator.server.springboot.core.domain.SpringBootService;
import tech.jhipster.forge.generator.server.springboot.web.domain.SpringBootWebDomainService;
import tech.jhipster.forge.generator.server.springboot.web.domain.SpringBootWebService;

@Configuration
public class SpringBootWebServiceBeanConfiguration {

  public final BuildToolRepository buildToolRepository;
  public final SpringBootService springBootService;

  public SpringBootWebServiceBeanConfiguration(BuildToolRepository buildToolRepository, SpringBootService springBootService) {
    this.buildToolRepository = buildToolRepository;
    this.springBootService = springBootService;
  }

  @Bean
  public SpringBootWebService springBootWebService() {
    return new SpringBootWebDomainService(buildToolRepository, springBootService);
  }
}
