package tech.jhipster.lite.generator.module.domain;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.project.domain.Project;

public record JHipsterModuleToApply(Project project, JHipsterModuleSlug slug, JHipsterModule module) {
  public JHipsterModuleToApply {
    Assert.notNull("project", project);
    Assert.notNull("slug", slug);
    Assert.notNull("module", module);
  }
}
