package tech.jhipster.forge.generator.server.springboot.database.psql.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.forge.generator.buildtool.maven.domain.MavenService;
import tech.jhipster.forge.generator.project.domain.ProjectRepository;
import tech.jhipster.forge.generator.server.springboot.core.domain.SpringBootService;
import tech.jhipster.forge.generator.server.springboot.database.psql.domain.PsqlDomainService;
import tech.jhipster.forge.generator.server.springboot.database.psql.domain.PsqlService;

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
