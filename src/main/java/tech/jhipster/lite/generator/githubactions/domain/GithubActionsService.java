package tech.jhipster.lite.generator.githubactions.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface GithubActionsService {
  void init(Project project);

  void addYml(Project project);
}
