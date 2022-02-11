package tech.jhipster.lite.generator.githubactions.domain;

import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class GithubActionsDomainService implements GithubActionsService {

  public static final String SOURCE = "githubactions";
  public static final String GITHUB_ACTIONS_FOLDER = ".github/workflows/";
  public static final String GITHUB_ACTIONS_YML = "github-actions.yml.mustache";

  private final ProjectRepository projectRepository;

  public GithubActionsDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void init(Project project) {
    addYml(project);
  }

  @Override
  public void addYml(Project project) {
    projectRepository.template(project, SOURCE, GITHUB_ACTIONS_YML, GITHUB_ACTIONS_FOLDER);
  }
}
