package tech.jhipster.lite.generator.setup.codespaces.domain;

import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class CodespacesDomainService implements CodespacesService {

  public static final String SOURCE = "setup/codespaces";
  public static final String DEVCONTAINER_DEST = ".devcontainer";

  private final ProjectRepository projectRepository;

  public CodespacesDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void init(Project project) {
    addJSON(project);
    addDocker(project);
  }

  public void addJSON(Project project) {
    projectRepository.add(project, SOURCE, "devcontainer.json", DEVCONTAINER_DEST, "devcontainer.json");
  }

  public void addDocker(Project project) {
    projectRepository.add(project, SOURCE, "Dockerfile", DEVCONTAINER_DEST, "Dockerfile");
  }
}
