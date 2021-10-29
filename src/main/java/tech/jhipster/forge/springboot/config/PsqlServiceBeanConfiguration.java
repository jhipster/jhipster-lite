package tech.jhipster.forge.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.generator.domain.buildtool.maven.MavenService;
import tech.jhipster.forge.generator.domain.server.springboot.core.SpringBootService;
import tech.jhipster.forge.springboot.domain.service.PsqlDomainService;
import tech.jhipster.forge.springboot.domain.usecase.PsqlService;

@Configuration
public class PsqlServiceBeanConfiguration {

  public final ProjectRepository projectRepository;
  public final MavenService mavenService;
  public final SpringBootService springBootService;

  public PsqlServiceBeanConfiguration(ProjectRepository projectRepository, MavenService mavenService, SpringBootService springBootService) {
    this.projectRepository = projectRepository;
    this.mavenService = mavenService;
    this.springBootService = springBootService;
  }

  @Bean
  public PsqlService psqlService() {
    return new PsqlDomainService(projectRepository, mavenService, springBootService);
  }
}
