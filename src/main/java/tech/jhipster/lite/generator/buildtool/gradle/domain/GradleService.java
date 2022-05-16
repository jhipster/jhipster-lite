package tech.jhipster.lite.generator.buildtool.gradle.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface GradleService {
  void initJava(Project project);

  void addJavaBuildGradleKts(Project project);
  void addGradleWrapper(Project project);
}
