package tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface MongockService {
  void init(Project project);

  void addMongockDependency(Project project);

  void addConfigurationJava(Project project);

  void addChangelogJava(Project project);

  void addProperties(Project project);
}
