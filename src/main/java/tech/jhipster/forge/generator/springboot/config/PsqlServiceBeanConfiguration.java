package tech.jhipster.forge.generator.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.generator.springboot.domain.service.PsqlDomainService;
import tech.jhipster.forge.generator.springboot.domain.usecase.MavenService;
import tech.jhipster.forge.generator.springboot.domain.usecase.PsqlService;
import tech.jhipster.forge.generator.springboot.domain.usecase.SpringBootService;

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
