package tech.jhipster.lite.generator.buildtool.gradle.domain;

import tech.jhipster.lite.generator.project.domain.Project;

import java.util.Optional;

public interface GradleService {

  void initJava(Project project);

  void addJavaBuildGradleKts(Project project);
  void addGradleWrapper(Project project);

  Optional<String> getVersion(String name);
}
