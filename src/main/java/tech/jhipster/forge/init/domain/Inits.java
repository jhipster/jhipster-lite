package tech.jhipster.forge.init.domain;

import tech.jhipster.forge.common.domain.Project;

public interface Inits {
  void init(Project project);

  void addPackageJson(Project project);
  void addReadme(Project project);
  void addGitConfiguration(Project project);
  void addEditorConfiguration(Project project);
  void addPrettier(Project project);
}
