package tech.jhipster.forge.generator.init.application;

import org.springframework.stereotype.Service;
import tech.jhipster.forge.generator.init.domain.InitService;
import tech.jhipster.forge.generator.project.domain.Project;

@Service
public class InitApplicationService {

  private final InitService initService;

  public InitApplicationService(InitService initService) {
    this.initService = initService;
  }

  public void init(Project project) {
    initService.init(project);
  }

  public void addPackageJson(Project project) {
    initService.addPackageJson(project);
  }

  public void addReadme(Project project) {
    initService.addReadme(project);
  }

  public void addGitConfiguration(Project project) {
    initService.addGitConfiguration(project);
  }

  public void addEditorConfiguration(Project project) {
    initService.addEditorConfiguration(project);
  }

  public void addPrettier(Project project) {
    initService.addPrettier(project);
  }
}
