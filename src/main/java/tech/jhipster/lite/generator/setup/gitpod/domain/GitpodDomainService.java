package tech.jhipster.lite.generator.setup.gitpod.domain;

import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class GitpodDomainService implements GitpodService {

  public static final String SOURCE = "setup/gitpod";

  private final ProjectRepository projectRepository;

  public GitpodDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void init(Project project) {
    addConfig(project);
    addDockerfile(project);
  }

  private void addConfig(Project project) {
    projectRepository.add(ProjectFile.forProject(project).withSource(SOURCE, ".gitpod.yml").withSameDestination());
  }

  private void addDockerfile(Project project) {
    projectRepository.add(ProjectFile.forProject(project).withSource(SOURCE, ".gitpod.Dockerfile").withSameDestination());
  }
}
