package tech.jhipster.lite.generator.buildtool.generic.domain;

import static tech.jhipster.lite.generator.project.domain.BuildToolType.MAVEN;

import java.util.List;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.maven.domain.MavenService;
import tech.jhipster.lite.generator.project.domain.BuildToolType;
import tech.jhipster.lite.generator.project.domain.Project;

public class BuildToolDomainService implements BuildToolService {

  public static final String EXCEPTION_NO_BUILD_TOOL = "No build tool";
  private final MavenService mavenService;

  public BuildToolDomainService(MavenService mavenService) {
    this.mavenService = mavenService;
  }

  @Override
  public void addParent(Project project, Parent parent) {
    if (project.isMavenProject()) {
      mavenService.addParent(project, parent);
    } else {
      throw new GeneratorException(EXCEPTION_NO_BUILD_TOOL);
    }
  }

  @Override
  public void addDependency(Project project, Dependency dependency) {
    if (project.isMavenProject()) {
      mavenService.addDependency(project, dependency);
    } else {
      throw new GeneratorException(EXCEPTION_NO_BUILD_TOOL);
    }
  }

  @Override
  public void addDependency(Project project, Dependency dependency, List<Dependency> exclusions) {
    if (project.isMavenProject()) {
      mavenService.addDependency(project, dependency, exclusions);
    } else {
      throw new GeneratorException(EXCEPTION_NO_BUILD_TOOL);
    }
  }

  @Override
  public void addDependencyManagement(Project project, Dependency dependency) {
    if (project.isMavenProject()) {
      mavenService.addDependencyManagement(project, dependency);
    } else {
      throw new GeneratorException(EXCEPTION_NO_BUILD_TOOL);
    }
  }

  @Override
  public void addPlugin(Project project, Plugin plugin) {
    if (project.isMavenProject()) {
      mavenService.addPlugin(project, plugin);
    } else {
      throw new GeneratorException(EXCEPTION_NO_BUILD_TOOL);
    }
  }

  @Override
  public void addPluginManagement(Project project, Plugin plugin) {
    if (project.isMavenProject()) {
      mavenService.addPluginManagement(project, plugin);
    } else {
      throw new GeneratorException(EXCEPTION_NO_BUILD_TOOL);
    }
  }

  @Override
  public void addProperty(Project project, String key, String version) {
    if (project.isMavenProject()) {
      mavenService.addProperty(project, key, version);
    } else {
      throw new GeneratorException(EXCEPTION_NO_BUILD_TOOL);
    }
  }

  @Override
  public void addRepository(Project project, Repository repository) {
    if (project.isMavenProject()) {
      mavenService.addRepository(project, repository);
    } else {
      throw new GeneratorException(EXCEPTION_NO_BUILD_TOOL);
    }
  }

  @Override
  public void init(Project project, BuildToolType buildTool) {
    if (buildTool == MAVEN) {
      mavenService.initJava(project);
    } else {
      throw new GeneratorException(EXCEPTION_NO_BUILD_TOOL);
    }
  }
}
