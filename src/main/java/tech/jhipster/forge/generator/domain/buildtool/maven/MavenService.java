package tech.jhipster.forge.generator.domain.buildtool.maven;

import java.util.List;
import tech.jhipster.forge.generator.domain.buildtool.Dependency;
import tech.jhipster.forge.generator.domain.buildtool.Parent;
import tech.jhipster.forge.generator.domain.buildtool.Plugin;
import tech.jhipster.forge.generator.domain.core.Project;

public interface MavenService {
  void addParent(Project project, Parent parent);
  void addDependency(Project project, Dependency dependency);
  void addDependency(Project project, Dependency dependency, List<Dependency> exclusions);
  void addPlugin(Project project, Plugin plugin);
  void addProperty(Project project, String key, String version);

  void init(Project project);

  void addPomXml(Project project);
  void addMavenWrapper(Project project);
}
