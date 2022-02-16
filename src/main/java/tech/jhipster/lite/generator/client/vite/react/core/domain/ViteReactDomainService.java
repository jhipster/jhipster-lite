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
    ViteReact.scripts().forEach((name, cmd) -> npmService.addScript(project, name, cmd));
    ViteReact.files().forEach(file -> projectRepository.add(project, SOURCE, file));
    ViteReact.reactFiles().forEach((file, path) -> projectRepository.template(project, SOURCE + "/" + path, file, getPath("/", path)));
  }
}
