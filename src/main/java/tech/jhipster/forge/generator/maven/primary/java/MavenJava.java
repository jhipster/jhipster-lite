package tech.jhipster.forge.generator.maven.primary.java;

import org.springframework.stereotype.Component;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.maven.application.MavenApplicationService;
import tech.jhipster.forge.generator.shared.domain.Dependency;
import tech.jhipster.forge.generator.shared.domain.Parent;
import tech.jhipster.forge.generator.shared.domain.Plugin;

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
}
