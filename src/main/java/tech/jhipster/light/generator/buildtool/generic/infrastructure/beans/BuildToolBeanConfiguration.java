package tech.jhipster.light.generator.buildtool.generic.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.light.generator.buildtool.generic.domain.BuildToolDomainService;
import tech.jhipster.light.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.light.generator.buildtool.maven.domain.MavenService;

@Configuration
public class BuildToolBeanConfiguration {

  private final MavenService mavenService;

  public BuildToolBeanConfiguration(MavenService mavenService) {
    this.mavenService = mavenService;
  }

  @Bean
  public BuildToolService buildToolService() {
    return new BuildToolDomainService(mavenService);
  }
}
