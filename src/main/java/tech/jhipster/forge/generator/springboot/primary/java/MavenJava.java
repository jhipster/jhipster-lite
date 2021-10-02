package tech.jhipster.forge.generator.springboot.primary.java;

import org.springframework.stereotype.Component;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.springboot.application.MavenApplicationService;
import tech.jhipster.forge.generator.springboot.domain.model.Dependency;
import tech.jhipster.forge.generator.springboot.domain.model.Parent;
import tech.jhipster.forge.generator.springboot.domain.model.Plugin;

@Component
public class MavenJava {

  private final MavenApplicationService mavenApplicationService;

  public MavenJava(MavenApplicationService mavenApplicationService) {
    this.mavenApplicationService = mavenApplicationService;
  }

  public void addParent(Project project, Parent parent) {
    mavenApplicationService.addParent(project, parent);
  }

  public void addDependency(Project project, Dependency dependency) {
    mavenApplicationService.addDependency(project, dependency);
  }

  public void addPlugin(Project project, Plugin plugin) {
    mavenApplicationService.addPlugin(project, plugin);
  }

  public void addPomXml(Project project) {
    mavenApplicationService.addPomXml(project);
  }

  public void addMavenWrapper(Project project) {
    mavenApplicationService.addMavenWrapper(project);
  }
}
