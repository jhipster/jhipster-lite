package tech.jhipster.forge.generator.application;

import org.springframework.stereotype.Service;
import tech.jhipster.forge.generator.domain.core.Project;
import tech.jhipster.forge.generator.domain.init.InitService;

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
