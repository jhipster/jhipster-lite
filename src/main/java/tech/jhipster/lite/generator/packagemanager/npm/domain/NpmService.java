package tech.jhipster.lite.generator.packagemanager.npm.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface NpmService {
  void addDependency(Project project, String dependency, String version);
  void addDevDependency(Project project, String dependency, String version);
  void addScript(Project project, String name, String cmd);

  void install(Project project);
  void prettify(Project project);
}
