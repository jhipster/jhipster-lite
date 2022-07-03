package tech.jhipster.lite.module.domain.javadependency;

import java.util.function.Function;
import tech.jhipster.lite.module.domain.javadependency.command.AddDirectJavaDependency;
import tech.jhipster.lite.module.domain.javadependency.command.AddJavaDependencyManagement;
import tech.jhipster.lite.module.domain.javadependency.command.JavaDependencyCommand;
import tech.jhipster.lite.module.domain.javadependency.command.RemoveDirectJavaDependency;
import tech.jhipster.lite.module.domain.javadependency.command.RemoveJavaDependencyManagement;

class DependenciesCommandsFactory {

  public static final DependenciesCommandsFactory MANAGEMENT = new DependenciesCommandsFactory(
    AddJavaDependencyManagement::new,
    RemoveJavaDependencyManagement::new
  );
  public static final DependenciesCommandsFactory DIRECT = new DependenciesCommandsFactory(
    AddDirectJavaDependency::new,
    RemoveDirectJavaDependency::new
  );

  private final Function<JavaDependency, JavaDependencyCommand> addDependency;
  private final Function<DependencyId, JavaDependencyCommand> removeDependency;

  private DependenciesCommandsFactory(
    Function<JavaDependency, JavaDependencyCommand> addDependency,
    Function<DependencyId, JavaDependencyCommand> removeDependency
  ) {
    this.addDependency = addDependency;
    this.removeDependency = removeDependency;
  }

  public JavaDependencyCommand addDependency(JavaDependency javaDependency) {
    return addDependency.apply(javaDependency);
  }

  public JavaDependencyCommand removeDependency(DependencyId id) {
    return removeDependency.apply(id);
  }
}
