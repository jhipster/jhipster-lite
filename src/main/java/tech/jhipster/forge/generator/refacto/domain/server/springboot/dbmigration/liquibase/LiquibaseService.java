package tech.jhipster.forge.generator.refacto.domain.server.springboot.dbmigration.liquibase;

import tech.jhipster.forge.generator.refacto.domain.core.Project;

public interface LiquibaseService {
  void init(Project project);

  void addLiquibase(Project project);
  void addChangelogMasterXml(Project project);
  void addConfigurationJava(Project project);
}
