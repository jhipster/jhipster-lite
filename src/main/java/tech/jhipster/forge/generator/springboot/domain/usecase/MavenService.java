package tech.jhipster.forge.generator.springboot.domain.usecase;

import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.springboot.domain.model.Dependency;
import tech.jhipster.forge.generator.springboot.domain.model.Parent;
import tech.jhipster.forge.generator.springboot.domain.model.Plugin;

public interface MavenService {
  void addParent(Project project, Parent parent);
  void addDependency(Project project, Dependency dependency);
  void addPlugin(Project project, Plugin plugin);

  void init(Project project);
  void addPomXml(Project project);
  void addMavenWrapper(Project project);
}
