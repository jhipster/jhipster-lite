package tech.jhipster.forge.generator.refacto.domain.server.springboot.web;

import tech.jhipster.forge.generator.refacto.domain.core.Project;

public interface SpringBootWebService {
  void init(Project project);

  void addSpringBootWeb(Project project);
  void addSpringBootUndertow(Project project);
}
