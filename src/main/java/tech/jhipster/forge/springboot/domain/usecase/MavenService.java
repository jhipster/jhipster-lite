package tech.jhipster.forge.springboot.domain.usecase;

import java.util.List;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.springboot.domain.model.Dependency;
import tech.jhipster.forge.springboot.domain.model.Parent;
import tech.jhipster.forge.springboot.domain.model.Plugin;

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
