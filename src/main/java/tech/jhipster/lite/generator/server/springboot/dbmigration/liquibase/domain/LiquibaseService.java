package tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface LiquibaseService {
  void init(Project project);

  void addLiquibase(Project project);
  void addChangelogMasterXml(Project project);
  void addConfigurationJava(Project project);
}
