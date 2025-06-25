package tech.jhipster.lite.generator.ci.github.actions.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.ci.github.actions.domain.GitHubActionsModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.nodejs.NodeVersions;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class GitHubActionsApplicationService {

  private final GitHubActionsModuleFactory gitHubActions;

  public GitHubActionsApplicationService(NodeVersions nodeVersions) {
    gitHubActions = new GitHubActionsModuleFactory(nodeVersions);
  }

  public JHipsterModule buildGitHubActionsMavenModule(JHipsterModuleProperties properties) {
    return gitHubActions.buildGitHubActionsMavenModule(properties);
  }

  public JHipsterModule buildGitHubActionsGradleModule(JHipsterModuleProperties properties) {
    return gitHubActions.buildGitHubActionsGradleModule(properties);
  }
}
