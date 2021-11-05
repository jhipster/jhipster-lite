package tech.jhipster.forge.generator.project.domain.added;

import tech.jhipster.forge.error.domain.Assert;
import tech.jhipster.forge.generator.project.domain.Parent;
import tech.jhipster.forge.generator.project.domain.Project;

public record ParentAdded(Project project, Parent parent) {
  public ParentAdded {
    Assert.notNull("project", project);
    Assert.notNull("parent", parent);
  }

  public static ParentAdded of(Project project, Parent parent) {
    return new ParentAdded(project, parent);
  }
}
