package tech.jhipster.lite.generator.client.react.cypress.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;

import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class CypressReactDomainService implements CypressReactService {

  public static final String SOURCE = "client/react";

  private final ProjectRepository projectRepository;
  private final NpmService npmService;

  public CypressReactDomainService(ProjectRepository projectRepository, NpmService npmService) {
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
    CypressReact
      .devDependencies()
      .forEach(dependency ->
        npmService
          .getVersionInReact(dependency)
          .ifPresentOrElse(
            version -> npmService.addDevDependency(project, dependency, version),
            () -> {
              throw new GeneratorException("DevDependency not found: " + dependency);
            }
          )
      );
  }

  public void addCypressScripts(Project project) {
    CypressReact.cypressScripts().forEach((name, cmd) -> npmService.addScript(project, name, cmd));
  }

  public void addCypressFiles(Project project) {
    CypressReact.cypressFiles().forEach((file, path) -> projectRepository.add(project, getPath(SOURCE, path), file, path));
  }

  public void addCypressTestFiles(Project project) {
    CypressReact.cypressTestFiles().forEach((file, path) -> projectRepository.template(project, getPath(SOURCE, path), file, path));
  }
}
