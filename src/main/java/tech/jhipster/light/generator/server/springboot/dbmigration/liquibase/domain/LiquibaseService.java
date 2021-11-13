package tech.jhipster.light.generator.server.springboot.dbmigration.liquibase.domain;

import tech.jhipster.light.generator.project.domain.Project;

public interface LiquibaseService {
  void init(Project project);

  void addLiquibase(Project project);
  void addChangelogMasterXml(Project project);
  void addConfigurationJava(Project project);
}
