package tech.jhipster.lite.generator.server.springboot.user.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.domain.LiquibaseDomainService;
import tech.jhipster.lite.generator.server.springboot.user.domain.SpringBootUserDomainService;
import tech.jhipster.lite.generator.server.springboot.user.domain.SpringBootUserService;

@Configuration
public class SpringBootUserConfiguration {

  private final ProjectRepository projectRepository;
  private final LiquibaseDomainService liquibaseDomainService;

  public SpringBootUserConfiguration(ProjectRepository projectRepository, LiquibaseDomainService liquibaseDomainService) {
    this.projectRepository = projectRepository;
    this.liquibaseDomainService = liquibaseDomainService;
  }

  @Bean
  public SpringBootUserService springBootUserService() {
    return new SpringBootUserDomainService(projectRepository, liquibaseDomainService);
  }
}
