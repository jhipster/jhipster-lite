package tech.jhipster.forge.generator.buildtool.generic.domain;

import java.util.List;
import tech.jhipster.forge.generator.project.domain.Project;

public interface BuildToolService {
  void addParent(Project project, Parent parent);
  void addDependency(Project project, Dependency dependency);
  void addDependency(Project project, Dependency dependency, List<Dependency> exclusions);
  void addPlugin(Project project, Plugin plugin);
  void addProperty(Project project, String key, String version);

  void init(Project project, BuildToolType buildTool);
}
