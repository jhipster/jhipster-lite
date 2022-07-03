package tech.jhipster.lite.module.domain.javadependency;

import java.util.Collection;
import java.util.stream.Stream;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.javadependency.command.JavaDependenciesCommands;
import tech.jhipster.lite.module.domain.javadependency.command.JavaDependencyCommand;

abstract class JavaDependencyCommandsCreator {

  private final JavaDependency dependency;

  JavaDependencyCommandsCreator(JavaDependency dependency) {
    Assert.notNull("dependency", dependency);

    this.dependency = dependency;
  }

  JavaDependenciesCommands changeCommands(CurrentJavaDependenciesVersions currentVersions, ProjectJavaDependencies projectDependencies) {
    Assert.notNull("currentVersion", currentVersions);
    Assert.notNull("projectDependencies", projectDependencies);

    Collection<JavaDependencyCommand> versionCommands = dependency.versionCommands(currentVersions, projectDependencies);
    Collection<JavaDependencyCommand> dependencyCommands = dependencyCommands(projectDependencies);

    return new JavaDependenciesCommands(Stream.of(versionCommands, dependencyCommands).flatMap(Collection::stream).toList());
  }

  protected JavaDependency dependency() {
    return dependency;
  }

  protected abstract Collection<JavaDependencyCommand> dependencyCommands(ProjectJavaDependencies projectDependencies);
}
