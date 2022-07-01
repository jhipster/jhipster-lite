package tech.jhipster.lite.module.domain.javadependency.command;

public sealed interface JavaDependencyCommand permits AddJavaDependency, RemoveJavaDependency, SetJavaDependencyVersion {
  JavaDependencyCommandType type();
}
