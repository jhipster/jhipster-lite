package tech.jhipster.forge.springboot.domain.usecase;

import tech.jhipster.forge.common.domain.Project;

public interface SpringBootWebService {
  void init(Project project);

  void addSpringBootWeb(Project project);
  void addSpringBootUndertow(Project project);
}
