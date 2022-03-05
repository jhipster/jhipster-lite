package tech.jhipster.lite.generator.githubactions.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.githubactions.domain.GithubActionsService;
import tech.jhipster.lite.generator.project.domain.Project;

@Service
public class GithubActionsApplicationService {

  private final GithubActionsService githubActionsService;

  public GithubActionsApplicationService(GithubActionsService githubActionsService) {
    this.githubActionsService = githubActionsService;
  }

  public void init(Project project) {
    githubActionsService.addGitHubActionsForMaven(project);
  }
}
