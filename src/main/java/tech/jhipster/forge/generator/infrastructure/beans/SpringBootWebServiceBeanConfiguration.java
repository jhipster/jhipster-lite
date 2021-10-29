package tech.jhipster.forge.generator.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.domain.buildtool.maven.MavenService;
import tech.jhipster.forge.generator.domain.server.springboot.core.SpringBootService;
import tech.jhipster.forge.generator.domain.server.springboot.web.SpringBootWebDomainService;
import tech.jhipster.forge.generator.domain.server.springboot.web.SpringBootWebService;

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
