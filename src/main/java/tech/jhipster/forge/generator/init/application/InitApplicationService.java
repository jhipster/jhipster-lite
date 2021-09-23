package tech.jhipster.forge.generator.init.application;

import org.springframework.stereotype.Component;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.generator.init.domain.InitDomainService;

@Component
public class InitApplicationService {

  private final InitDomainService initFactory;

  public InitApplicationService(ProjectRepository projectRepository) {
    this.initFactory = new InitDomainService(projectRepository);
  }

  public void init(Project project) {
    initFactory.init(project);
  }

  public void addPackageJson(Project project) {
    initFactory.addPackageJson(project);
  }

  public void addReadme(Project project) {
    initFactory.addReadme(project);
  }

  public void addGitConfiguration(Project project) {
    initFactory.addGitConfiguration(project);
  }

  public void addEditorConfiguration(Project project) {
    initFactory.addEditorConfiguration(project);
  }

  public void addPrettier(Project project) {
    initFactory.addPrettier(project);
  }
}
