package tech.jhipster.forge.generator.application;

import org.springframework.stereotype.Service;
import tech.jhipster.forge.generator.domain.core.Project;
import tech.jhipster.forge.generator.domain.server.springboot.dbmigration.liquibase.LiquibaseService;

@Service
public class LiquibaseApplicationService {

  private final LiquibaseService liquibaseService;

  public LiquibaseApplicationService(LiquibaseService liquibaseService) {
    this.liquibaseService = liquibaseService;
  }

  public void init(Project project) {
    liquibaseService.init(project);
  }

  public void addLiquibase(Project project) {
    liquibaseService.addLiquibase(project);
  }

  void addChangelogMasterXml(Project project) {
    liquibaseService.addChangelogMasterXml(project);
  }

  void addConfigurationJava(Project project) {
    liquibaseService.addConfigurationJava(project);
  }
}
