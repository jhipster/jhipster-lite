package tech.jhipster.forge.generator.springboot.domain.usecase;

import tech.jhipster.forge.common.domain.Project;

public interface InitService {
  void init(Project project);
  void addPackageJson(Project project);
  void addReadme(Project project);
  void addGitConfiguration(Project project);
  void addEditorConfiguration(Project project);
  void addPrettier(Project project);
}
