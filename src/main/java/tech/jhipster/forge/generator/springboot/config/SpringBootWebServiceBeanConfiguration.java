package tech.jhipster.forge.generator.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.springboot.domain.service.SpringBootWebDomainService;
import tech.jhipster.forge.generator.springboot.domain.usecase.MavenService;
import tech.jhipster.forge.generator.springboot.domain.usecase.SpringBootService;
import tech.jhipster.forge.generator.springboot.domain.usecase.SpringBootWebService;

@Configuration
public class SpringBootWebServiceBeanConfiguration {

  public final MavenService mavenService;
  public final SpringBootService springBootService;

  public SpringBootWebServiceBeanConfiguration(MavenService mavenService, SpringBootService springBootService) {
    this.mavenService = mavenService;
    this.springBootService = springBootService;
  }

  @Bean
  public SpringBootWebService springBootWebService() {
    return new SpringBootWebDomainService(mavenService, springBootService);
  }
}
