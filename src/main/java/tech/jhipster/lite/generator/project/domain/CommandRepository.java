package tech.jhipster.lite.generator.project.domain;

public interface CommandRepository {
  void npmInstall(Project project);
  void npmPrettierFormat(Project project);
}
