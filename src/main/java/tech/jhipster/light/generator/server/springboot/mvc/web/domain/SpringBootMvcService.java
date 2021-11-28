package tech.jhipster.light.generator.server.springboot.mvc.web.domain;

import tech.jhipster.light.generator.project.domain.Project;

public interface SpringBootMvcService {
  void init(Project project);

  void addSpringBootMvc(Project project);
  void addSpringBootUndertow(Project project);
  void addExceptionHandler(Project project);
}
