package tech.jhipster.lite.module.domain.javadependency.command;

public sealed interface JavaDependencyCommand
  permits
    RemoveJavaDependencyManagement,
    AddJavaDependencyManagement,
    AddDirectJavaDependency,
    RemoveDirectJavaDependency,
    SetJavaDependencyVersion {
  JavaDependencyCommandType type();
}
