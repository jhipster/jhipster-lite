package tech.jhipster.lite.generator.setup.codespaces.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface CodespacesService {
  void addJSON(Project project);
  void addDocker(Project project);
  void init(Project project);
}
