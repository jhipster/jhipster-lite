package tech.jhipster.forge.generator.init.domain;

import tech.jhipster.forge.generator.refacto.domain.core.Project;

public interface InitService {
  void init(Project project);

  void addPackageJson(Project project);
  void addReadme(Project project);
  void addGitConfiguration(Project project);
  void addEditorConfiguration(Project project);
  void addPrettier(Project project);
}
