package tech.jhipster.lite.generator.client.vite.react.cypress.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;

import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class CypressViteReactDomainService implements CypressViteReactService {

  public static final String SOURCE = "client/vite/react";

  private final ProjectRepository projectRepository;
  private final NpmService npmService;

  public CypressViteReactDomainService(ProjectRepository projectRepository, NpmService npmService) {
    this.projectRepository = projectRepository;
    this.npmService = npmService;
  }

  @Override
  public void init(Project project) {
    addDevDependencies(project);
    addCypressScripts(project);
    addCypressFiles(project);
    addCypressTestFiles(project);
  }

  public void addDevDependencies(Project project) {
    CypressViteReact
      .devDependencies()
      .forEach(dependency ->
        npmService
          .getVersionInViteReact(dependency)
          .ifPresentOrElse(
            version -> npmService.addDevDependency(project, dependency, version),
            () -> {
              throw new GeneratorException("DevDependency not found: " + dependency);
            }
          )
      );
  }

  public void addCypressScripts(Project project) {
    CypressViteReact.cypressScripts().forEach((name, cmd) -> npmService.addScript(project, name, cmd));
  }

  public void addCypressFiles(Project project) {
    CypressViteReact.cypressFiles().forEach((file, path) -> projectRepository.add(project, getPath(SOURCE, path), file, path));
  }

  public void addCypressTestFiles(Project project) {
    CypressViteReact.cypressTestFiles().forEach((file, path) -> projectRepository.template(project, getPath(SOURCE, path), file, path));
  }
}
