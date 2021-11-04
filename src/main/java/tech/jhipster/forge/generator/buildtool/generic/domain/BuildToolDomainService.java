package tech.jhipster.forge.generator.buildtool.generic.domain;

import java.util.List;
import tech.jhipster.forge.error.domain.GeneratorException;
import tech.jhipster.forge.generator.buildtool.maven.domain.MavenService;
import tech.jhipster.forge.generator.project.domain.Dependency;
import tech.jhipster.forge.generator.project.domain.Parent;
import tech.jhipster.forge.generator.project.domain.Plugin;
import tech.jhipster.forge.generator.project.domain.Project;

public class BuildToolDomainService implements BuildToolService {

  private final MavenService mavenService;

  public BuildToolDomainService(MavenService mavenService) {
    this.mavenService = mavenService;
  }

  @Override
  public void addParent(Project project, Parent parent) {
    if (project.isMavenProject()) {
      mavenService.addParent(project, parent);
    } else {
      throw new GeneratorException("No build tool");
    }
  }

  @Override
  public void addDependency(Project project, Dependency dependency) {
    addDependency(project, dependency, List.of());
  }

  @Override
  public void addDependency(Project project, Dependency dependency, List<Dependency> exclusions) {
    if (project.isMavenProject()) {
      mavenService.addDependency(project, dependency, exclusions);
    } else {
      throw new GeneratorException("No build tool");
    }
  }

  @Override
  public void addPlugin(Project project, Plugin plugin) {
    if (project.isMavenProject()) {
      mavenService.addPlugin(project, plugin);
    } else {
      throw new GeneratorException("No build tool");
    }
  }

  @Override
  public void addProperty(Project project, String key, String version) {
    if (project.isMavenProject()) {
      mavenService.addProperty(project, key, version);
    } else {
      throw new GeneratorException("No build tool");
    }
  }
}
