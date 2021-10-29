package tech.jhipster.forge.generator.domain.server.springboot.web;

import tech.jhipster.forge.generator.domain.core.Project;

public interface SpringBootWebService {
  void init(Project project);

  void addSpringBootWeb(Project project);
  void addSpringBootUndertow(Project project);
}
