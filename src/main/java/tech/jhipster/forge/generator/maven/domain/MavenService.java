package tech.jhipster.forge.generator.maven.domain;

import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.shared.domain.Dependency;
import tech.jhipster.forge.generator.shared.domain.Parent;
import tech.jhipster.forge.generator.shared.domain.Plugin;

public interface MavenService {
  void initPomXml(Project project);
  void addMavenWrapper(Project project);
  void addParent(Project project, Parent parent);
  void addDependency(Project project, Dependency dependency);
  void addPlugin(Project project, Plugin plugin);
}
