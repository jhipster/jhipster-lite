package tech.jhipster.forge.generator.springboot.application;

import java.util.List;
import org.springframework.stereotype.Component;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.springboot.domain.model.Dependency;
import tech.jhipster.forge.generator.springboot.domain.model.Parent;
import tech.jhipster.forge.generator.springboot.domain.model.Plugin;
import tech.jhipster.forge.generator.springboot.domain.usecase.MavenService;

@Component
public class MavenApplicationService {

  private final MavenService mavenService;

  public MavenApplicationService(MavenService mavenService) {
    this.mavenService = mavenService;
  }

  public void addParent(Project project, Parent parent) {
    mavenService.addParent(project, parent);
  }

  public void addDependency(Project project, Dependency dependency) {
    mavenService.addDependency(project, dependency);
  }

  public void addDependency(Project project, Dependency dependency, List<Dependency> exclusions) {
    mavenService.addDependency(project, dependency, exclusions);
  }

  public void addPlugin(Project project, Plugin plugin) {
    mavenService.addPlugin(project, plugin);
  }

  public void init(Project project) {
    mavenService.init(project);
  }

  public void addPomXml(Project project) {
    mavenService.addPomXml(project);
  }

  public void addMavenWrapper(Project project) {
    mavenService.addMavenWrapper(project);
  }
}
