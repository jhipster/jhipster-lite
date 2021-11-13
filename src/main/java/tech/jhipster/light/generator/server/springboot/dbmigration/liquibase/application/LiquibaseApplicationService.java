package tech.jhipster.light.generator.server.springboot.dbmigration.liquibase.application;

import org.springframework.stereotype.Service;
import tech.jhipster.light.generator.project.domain.Project;
import tech.jhipster.light.generator.server.springboot.dbmigration.liquibase.domain.LiquibaseService;

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
