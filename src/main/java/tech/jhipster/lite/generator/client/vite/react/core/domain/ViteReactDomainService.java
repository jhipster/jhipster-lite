package tech.jhipster.lite.generator.client.vite.react.core.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;

import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class ViteReactDomainService implements ViteReactService {

  public static final String SOURCE = "client/vite/react";

  private final ProjectRepository projectRepository;
  private final NpmService npmService;

  public ViteReactDomainService(ProjectRepository projectRepository, NpmService npmService) {
    this.projectRepository = projectRepository;
    this.npmService = npmService;
  }

  @Override
  public void init(Project project) {
    addDevDependencies(project);
    addDependencies(project);
    addScripts(project);
    addFiles(project);
    addViteReactFiles(project);
  }

  public void addDevDependencies(Project project) {
    ViteReact
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

  public void addDependencies(Project project) {
    ViteReact
      .dependencies()
      .forEach(dependency ->
        npmService
          .getVersionInViteReact(dependency)
          .ifPresentOrElse(
            version -> npmService.addDependency(project, dependency, version),
            () -> {
              throw new GeneratorException("Dependency not found: " + dependency);
            }
          )
      );
  }

  public void addScripts(Project project) {
    ViteReact.scripts().forEach((name, cmd) -> npmService.addScript(project, name, cmd));
  }

  public void addFiles(Project project) {
    ViteReact.files().forEach(file -> projectRepository.add(project, SOURCE, file));
  }

  public void addViteReactFiles(Project project) {
    ViteReact.reactFiles().forEach((file, path) -> projectRepository.template(project, getPath(SOURCE, path), file, path));
  }
}
