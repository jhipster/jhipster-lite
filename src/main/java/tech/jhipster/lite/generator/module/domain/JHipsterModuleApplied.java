package tech.jhipster.lite.generator.module.domain;

import java.time.Instant;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.project.domain.Project;

public record JHipsterModuleApplied(Project project, JHipsterModuleSlug slug, Instant time) {
  public JHipsterModuleApplied {
    Assert.notNull("project", project);
    Assert.notNull("slug", slug);
    Assert.notNull("time", time);
  }
}
