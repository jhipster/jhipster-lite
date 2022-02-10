package tech.jhipster.lite.generator.setup.codespace.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface CodespaceService {
  void addJSON(Project project);
  void addDocker(Project project);
  void init(Project project);
}
