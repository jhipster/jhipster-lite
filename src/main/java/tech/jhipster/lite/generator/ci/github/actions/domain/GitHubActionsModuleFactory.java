package tech.jhipster.lite.generator.ci.github.actions.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.to;

import java.util.Locale;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.javabuild.JavaBuildTool;
import tech.jhipster.lite.module.domain.npm.NpmVersions;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class GitHubActionsModuleFactory {

  private static final JHipsterSource SOURCE = from("ci/github/actions/.github");

  private final NpmVersions npmVersions;

  public GitHubActionsModuleFactory(NpmVersions npmVersions) {
    this.npmVersions = npmVersions;
  }

  public JHipsterModule buildGitHubActionsMavenModule(JHipsterModuleProperties properties) {
    return buildGitHubActionsModule(properties, JavaBuildTool.MAVEN);
  }

  public JHipsterModule buildGitHubActionsGradleModule(JHipsterModuleProperties properties) {
    return buildGitHubActionsModule(properties, JavaBuildTool.GRADLE);
  }

  private JHipsterModule buildGitHubActionsModule(JHipsterModuleProperties properties, JavaBuildTool javaBuildTool) {
    Assert.notNull("properties", properties);

    // @formatter:off
    return moduleBuilder(properties)
      .context()
        .put("nodeVersion", npmVersions.nodeVersion().get())
        .put(javaBuildTool.name().toLowerCase(Locale.ROOT), true)
        .and()
      .files()
        .add(SOURCE.template("workflows/github-actions.yml"), to(".github/workflows/github-actions.yml"))
        .and()
      .build();
    // @formatter:on
  }
}
