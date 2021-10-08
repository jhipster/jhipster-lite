package tech.jhipster.forge.generator.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.generator.springboot.domain.service.PostgreSQLDomainService;
import tech.jhipster.forge.generator.springboot.domain.usecase.MavenService;
import tech.jhipster.forge.generator.springboot.domain.usecase.PostgreSQLService;
import tech.jhipster.forge.generator.springboot.domain.usecase.SpringBootService;

@Configuration
public class PostgreSQLServiceBeanConfiguration {

  public final ProjectRepository projectRepository;
  public final MavenService mavenService;
  public final SpringBootService springBootService;

  public PostgreSQLServiceBeanConfiguration(
    ProjectRepository projectRepository,
    MavenService mavenService,
    SpringBootService springBootService
  ) {
    this.projectRepository = projectRepository;
    this.mavenService = mavenService;
    this.springBootService = springBootService;
  }

  @Bean
  public PostgreSQLService postgreSQLService() {
    return new PostgreSQLDomainService(projectRepository, mavenService, springBootService);
  }
}
