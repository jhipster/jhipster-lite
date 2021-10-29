package tech.jhipster.forge.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.domain.buildtool.maven.MavenService;
import tech.jhipster.forge.springboot.domain.service.SpringBootWebDomainService;
import tech.jhipster.forge.springboot.domain.usecase.SpringBootService;
import tech.jhipster.forge.springboot.domain.usecase.SpringBootWebService;

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
