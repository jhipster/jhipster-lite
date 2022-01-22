package tech.jhipster.lite.generator.client.angular.core.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_WEBAPP;

import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class AngularDomainService implements AngularService {

  public static final String SOURCE = "client/angular";
  public static final String SOURCE_WEBAPP = "client/angular/src/main/webapp";

  private final ProjectRepository projectRepository;
  private final NpmService npmService;

  public AngularDomainService(ProjectRepository projectRepository, NpmService npmService) {
    this.projectRepository = projectRepository;
    this.npmService = npmService;
  }

  @Override
  public void init(Project project) {
    Angular
      .devDependencies()
      .forEach(dependency ->
        npmService.getVersion(dependency).ifPresent(version -> npmService.addDevDependency(project, dependency, version))
      );
    Angular
      .dependencies()
      .forEach(dependency -> npmService.getVersion(dependency).ifPresent(version -> npmService.addDependency(project, dependency, version))
      );
    Angular.scripts().forEach((name, cmd) -> npmService.addScript(project, name, cmd));
    Angular.files().forEach(file -> projectRepository.add(project, SOURCE, file));
    Angular
      .angularFiles()
      .forEach((file, path) -> projectRepository.template(project, SOURCE_WEBAPP + "/" + path, file, getPath(MAIN_WEBAPP, path)));
  }
}
