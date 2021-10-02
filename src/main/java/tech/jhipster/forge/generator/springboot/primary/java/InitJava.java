package tech.jhipster.forge.generator.springboot.primary.java;

import org.springframework.stereotype.Component;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.generator.springboot.application.InitApplicationService;

@Component
public class InitJava {

  private final InitApplicationService initApplicationService;

  public InitJava(InitApplicationService initApplicationService) {
    this.initApplicationService = initApplicationService;
  }

  public void init(Project project) {
    initApplicationService.init(project);
  }

  public void addPackageJson(Project project) {
    initApplicationService.addPackageJson(project);
  }

  public void addReadme(Project project) {
    initApplicationService.addReadme(project);
  }

  public void addGitConfiguration(Project project) {
    initApplicationService.addGitConfiguration(project);
  }

  public void addEditorConfiguration(Project project) {
    initApplicationService.addEditorConfiguration(project);
  }

  public void addPrettier(Project project) {
    initApplicationService.addPrettier(project);
  }
}
