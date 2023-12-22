package tech.jhipster.lite.module.infrastructure.secondary.javadependency;

import tech.jhipster.lite.module.domain.javabuild.command.*;

public interface JavaDependenciesCommandHandler {
  void handle(SetVersion command);

  void handle(AddDirectJavaDependency command);

  void handle(RemoveDirectJavaDependency command);

  void handle(RemoveJavaDependencyManagement command);

  void handle(AddJavaDependencyManagement command);

  void handle(AddDirectJavaBuildPlugin command);

  void handle(AddBuildPluginManagement command);

  void handle(AddMavenBuildExtension command);

  void handle(SetBuildProperty command);

  void handle(AddJavaBuildProfile addJavaBuildProfile);
}
