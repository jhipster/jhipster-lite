package tech.jhipster.lite.generator.ci.github.actions.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.ci.github.actions.domain.GitHubActionsModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class GitHubActionsApplicationService {

  private final GitHubActionsModuleFactory factory;

  public GitHubActionsApplicationService() {
    factory = new GitHubActionsModuleFactory();
  }

  public JHipsterModule buildGitHubActionsMavenModule(JHipsterModuleProperties properties) {
    return factory.buildGitHubActionsMavenModule(properties);
  }

  public JHipsterModule buildGitHubActionsGradleModule(JHipsterModuleProperties properties) {
    return factory.buildGitHubActionsGradleModule(properties);
  }
}
