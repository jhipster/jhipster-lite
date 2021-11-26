package tech.jhipster.light.generator.server.springboot.web.domain;

import tech.jhipster.light.generator.project.domain.Project;

public interface SpringBootWebService {
  void init(Project project);

  void addSpringBootWeb(Project project);
  void addSpringBootUndertow(Project project);
  void addExceptionHandler(Project project);
}
