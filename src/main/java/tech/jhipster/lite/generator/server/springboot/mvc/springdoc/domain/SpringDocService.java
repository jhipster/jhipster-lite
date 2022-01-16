package tech.jhipster.lite.generator.server.springboot.mvc.springdoc.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface SpringDocService {
  void init(Project project);

  void addSpringDocDependency(Project project);

  void addJavaFiles(Project project);

  void addProperties(Project project);
}
