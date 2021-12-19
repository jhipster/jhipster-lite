package tech.jhipster.lite.generator.server.sonar.domain;

import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class SonarDomainService implements SonarService {

  private final ProjectRepository projectRepository;

  public SonarDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void init(Project project) {}
}
