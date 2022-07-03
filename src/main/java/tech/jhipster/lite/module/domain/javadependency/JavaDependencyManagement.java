package tech.jhipster.lite.module.domain.javadependency;

import java.util.Collection;
import tech.jhipster.lite.module.domain.javadependency.command.JavaDependencyCommand;

public class JavaDependencyManagement extends JavaDependencyCommandsCreator {

  JavaDependencyManagement(JavaDependency dependency) {
    super(dependency);
  }

  @Override
  protected Collection<JavaDependencyCommand> dependencyCommands(ProjectJavaDependencies projectDependencies) {
    return dependency()
      .dependencyCommands(DependenciesCommandsFactory.MANAGEMENT, projectDependencies.dependencyManagement(dependency().id()));
  }
}
