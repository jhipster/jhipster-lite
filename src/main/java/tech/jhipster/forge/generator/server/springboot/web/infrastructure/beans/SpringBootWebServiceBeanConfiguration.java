package tech.jhipster.forge.generator.server.springboot.web.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.buildtool.maven.domain.MavenService;
import tech.jhipster.forge.generator.server.springboot.core.domain.SpringBootService;
import tech.jhipster.forge.generator.server.springboot.web.domain.SpringBootWebDomainService;
import tech.jhipster.forge.generator.server.springboot.web.domain.SpringBootWebService;

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
