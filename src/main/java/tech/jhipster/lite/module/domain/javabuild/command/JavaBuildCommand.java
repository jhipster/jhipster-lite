package tech.jhipster.lite.module.domain.javabuild.command;

public sealed interface JavaBuildCommand
  permits
    AddBuildPluginManagement,
    AddDirectJavaBuildPlugin,
    AddDirectJavaDependency,
    AddGradlePlugin,
    AddJavaBuildProfile,
    AddJavaDependencyManagement,
    AddMavenBuildExtension,
    RemoveDirectJavaDependency,
    RemoveJavaDependencyManagement,
    SetBuildProperty,
    SetVersion {}
