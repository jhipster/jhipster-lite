package tech.jhipster.forge.generator.buildtool.generic.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.buildtool.generic.domain.BuildToolDomainService;
import tech.jhipster.forge.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.forge.generator.buildtool.maven.domain.MavenService;

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
