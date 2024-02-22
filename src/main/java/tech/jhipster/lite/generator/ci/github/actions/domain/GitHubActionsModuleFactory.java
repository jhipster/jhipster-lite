package tech.jhipster.lite.generator.ci.github.actions.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class GitHubActionsModuleFactory {

  private static final JHipsterSource SOURCE = from("ci/github/actions/.github");
  private static final String ACTIONS_SETUP_ACTION_YML = "actions/setup/action.yml";

  public JHipsterModule buildGitHubActionsMavenModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    return moduleBuilder(properties)
      .files()
      .add(SOURCE.template(ACTIONS_SETUP_ACTION_YML), to(".github/actions/setup/action.yml"))
      .add(SOURCE.template("workflows/github-actions-maven.yml"), to(".github/workflows/github-actions.yml"))
      .and()
      .build();
  }

  public JHipsterModule buildGitHubActionsGradleModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    return moduleBuilder(properties)
      .files()
      .add(SOURCE.template(ACTIONS_SETUP_ACTION_YML), to(".github/actions/setup/action.yml"))
      .add(SOURCE.template("workflows/github-actions-gradle.yml"), to(".github/workflows/github-actions.yml"))
      .and()
      .build();
  }
}
