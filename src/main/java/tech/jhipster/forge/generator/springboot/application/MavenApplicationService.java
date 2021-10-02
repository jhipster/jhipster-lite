package tech.jhipster.forge.generator.springboot.application;

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

  public void addPlugin(Project project, Plugin plugin) {
    mavenService.addPlugin(project, plugin);
  }

  public void initPomXml(Project project) {
    mavenService.initPomXml(project);
  }

  public void addMavenWrapper(Project project) {
    mavenService.addMavenWrapper(project);
  }
}
