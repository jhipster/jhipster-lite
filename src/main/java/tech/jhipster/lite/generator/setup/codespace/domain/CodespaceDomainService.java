package tech.jhipster.lite.generator.setup.codespace.domain;

import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;

import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class CodespaceDomainService implements CodespaceService {

  public static final String SOURCE = "setup/Codespace";
  public static final String DEVCONTAINER_DEST = ".devcontainer";

  private final ProjectRepository projectRepository;

  public CodespaceDomainService(ProjectRepository projectRepository) {
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
