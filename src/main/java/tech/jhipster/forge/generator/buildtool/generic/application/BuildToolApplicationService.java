package tech.jhipster.forge.generator.buildtool.generic.application;

import java.util.List;
import org.springframework.stereotype.Component;
import tech.jhipster.forge.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.forge.generator.project.domain.Dependency;
import tech.jhipster.forge.generator.project.domain.Parent;
import tech.jhipster.forge.generator.project.domain.Plugin;
import tech.jhipster.forge.generator.project.domain.Project;

@Component
public class BuildToolApplicationService {

  private final BuildToolService buildToolService;

  public BuildToolApplicationService(BuildToolService buildToolService) {
    this.buildToolService = buildToolService;
  }

  public void addParent(Project project, Parent parent) {
    buildToolService.addParent(project, parent);
  }

  public void addDependency(Project project, Dependency dependency) {
    addDependency(project, dependency, List.of());
  }

  public void addDependency(Project project, Dependency dependency, List<Dependency> exclusions) {
    buildToolService.addDependency(project, dependency, exclusions);
  }

  public void addPlugin(Project project, Plugin plugin) {
    buildToolService.addPlugin(project, plugin);
  }

  public void addProperty(Project project, String key, String version) {
    buildToolService.addProperty(project, key, version);
  }
}
