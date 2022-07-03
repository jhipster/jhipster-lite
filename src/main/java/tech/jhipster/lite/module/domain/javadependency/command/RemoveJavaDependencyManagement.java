package tech.jhipster.lite.module.domain.javadependency.command;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.javadependency.DependencyId;

public record RemoveJavaDependencyManagement(DependencyId dependency) implements JavaDependencyCommand {
  public RemoveJavaDependencyManagement {
    Assert.notNull("dependency", dependency);
  }

  @Override
  public JavaDependencyCommandType type() {
    return JavaDependencyCommandType.REMOVE_DEPENDENCY_MANAGEMENT;
  }
}
