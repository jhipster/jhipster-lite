package tech.jhipster.lite.generator.ci.github.actions.domain;

import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class GithubActionsDomainService implements GithubActionsService {

  public static final String GITHUB_ACTIONS_CI_SOURCE_FOLDER = "ci/github/actions/.github/workflows/";
  public static final String GITHUB_ACTIONS_CI_YML = "github-actions.yml.mustache";
  public static final String GITHUB_ACTIONS_CI_DESTINATION_FOLDER = ".github/workflows/";

  public static final String GITHUB_ACTIONS_SETUP_SOURCE_FOLDER = "ci/github/actions/.github/actions/setup/";
  public static final String GITHUB_ACTIONS_SETUP_YML = "action.yml.mustache";
  public static final String GITHUB_ACTIONS_SETUP_DESTINATION_FOLDER = ".github/actions/setup/";

  private final ProjectRepository projectRepository;

  public GithubActionsDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void addGitHubActionsForMaven(Project project) {
    addYmls(project);
  }

  private void addYmls(Project project) {
    projectRepository.template(
      project,
      GITHUB_ACTIONS_SETUP_SOURCE_FOLDER,
      GITHUB_ACTIONS_SETUP_YML,
      GITHUB_ACTIONS_SETUP_DESTINATION_FOLDER
    );
    projectRepository.template(project, GITHUB_ACTIONS_CI_SOURCE_FOLDER, GITHUB_ACTIONS_CI_YML, GITHUB_ACTIONS_CI_DESTINATION_FOLDER);
  }
}
